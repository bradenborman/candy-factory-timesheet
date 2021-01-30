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

@Profile({"!local"})
@Service
public class CronService {

    private final Logger logger = LoggerFactory.getLogger(CronService.class);

    public CronService() {
        logger.info("Setting up cron service, Sending email at 9:30 pm");
    }

    @Autowired
    EmailSendingService emailSendingService;

    @Autowired
    DataRetrievalService dataRetrievalService;

    @Scheduled(cron = "0 30 21 * * ?") //9:30 PM
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

        emailSendingService.sendTestEmail(excelFile);
    }

}