package cftimesheet.config;

import cftimesheet.models.ShiftDetails;
import cftimesheet.services.DataRetrievalService;
import cftimesheet.services.EmailSendingService;
import cftimesheet.services.ExcelReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static cftimesheet.models.ExcelReportHeaders.*;


@Configuration
@EnableScheduling
public class AppConfig {

    private final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    EmailSendingService emailSendingService;

    @Autowired
    DataRetrievalService dataRetrievalService;

    @Scheduled(cron = "0 2/1 * ? * *")
    public void sendEightPmReportReal() {
        logger.info("Task Hit");
        ExcelReportService excelReportService = new ExcelReportService();
        List<ShiftDetails> shiftsWorkedToday = dataRetrievalService.fetchShiftsToday();
        ByteArrayResource excelFile = excelReportService
                .setShiftsWorked(shiftsWorkedToday)
                .withStartingWorkbook()
                .withHeaders(NAME, PHONE, EMAIL, ADDRESS, CLOCK_IN, CLOCK_OUT, TOTAL_TIME_WORKED)
                .createRowsFromShiftsWorked()
                .autoSizeColumns()
                .toFile();

        emailSendingService.sendTestEmail(excelFile);
    }

}