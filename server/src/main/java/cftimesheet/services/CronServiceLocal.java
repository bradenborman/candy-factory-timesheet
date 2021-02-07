package cftimesheet.services;

import cftimesheet.models.ShiftDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static cftimesheet.models.ExcelReportHeaders.*;

@Profile({"local"})
@Service
public class CronServiceLocal {

    private final Logger logger = LoggerFactory.getLogger(CronServiceLocal.class);

    public CronServiceLocal() {
        logger.info("Setting up cron service. Every 5 mins");
    }

    @Autowired
    EmailSendingService emailSendingService;

    @Autowired
    DataRetrievalService dataRetrievalService;

    @Scheduled(cron = "0 */2 * ? * *") // Every 5 minutes
    public void sendEightPmReportReal() {
        logger.info("sendEightPmReportReal Task Hit");
        ExcelReportService excelReportService = new ExcelReportService();
        List<ShiftDetails> shiftsWorkedToday = dataRetrievalService.fetchShiftsToday();
        ByteArrayResource excelFile = excelReportService
                .setShiftsWorked(shiftsWorkedToday)
                .withStartingWorkbook()
                .withHeaders(L_NAME, F_NAME, PHONE, EMAIL, ADDRESS, VENMO, PAYPAL, CLOCK_IN, CLOCK_OUT, TOTAL_TIME_WORKED, TOTAL_TIME_WORKED_MIN_TOTAL)
                .createRowsFromShiftsWorked()
                .autoSizeColumns()
                .toFile();

        int attempts = 0;

        do {
            try {
//                emailSendingService.sendWorksheetEmail(excelFile);
                attempts = 3;
            }
            catch (Exception e){
                logger.info("Failed to send email. Trying again");
                attempts = attempts + 1;
            }
        } while (attempts < 3);
    }

}