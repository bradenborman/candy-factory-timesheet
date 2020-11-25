package cftimesheet.services;

import cftimesheet.models.ExcelReportHeaders;
import cftimesheet.models.ShiftDetails;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ExcelReportService {

    private final Logger logger = LoggerFactory.getLogger(ExcelReportService.class);

    private final String SHEET_NAME = "Employee Data";
    private List<ShiftDetails> shiftsWorked;
    private XSSFWorkbook workbook;
    private List<ExcelReportHeaders> PAGE_HEADERS = new ArrayList<>();

    public ExcelReportService setShiftsWorked(List<ShiftDetails> shiftsWorked) {
        this.shiftsWorked = shiftsWorked;
        return this;
    }

    public ExcelReportService withStartingWorkbook() {
        workbook = new XSSFWorkbook();
        return this;
    }

    public ExcelReportService withHeaders(ExcelReportHeaders... headersToSet) {
        PAGE_HEADERS.addAll(Arrays.asList(headersToSet));
        XSSFSheet sheet = workbook.createSheet(SHEET_NAME);
        Row sheetHeader = sheet.createRow(0);
        for (int i = 0; i < PAGE_HEADERS.size(); i++) {
            sheetHeader.createCell(i).setCellValue(PAGE_HEADERS.get(i).getDisplayName());
        }
        return this;
    }

    public ExcelReportService createRowsFromShiftsWorked() {
        XSSFSheet sheet = workbook.getSheet(SHEET_NAME);
        for (int i = 0; i < shiftsWorked.size(); i++) {
            ShiftDetails shiftDetails = shiftsWorked.get(i);
            int rowIndex = i + 1;
            sheet.createRow(rowIndex);
            for (int j = 0; j < PAGE_HEADERS.size(); j++) {
                ExcelReportHeaders excelReportHeaders = PAGE_HEADERS.get(j);
                sheet.getRow(rowIndex).createCell(j).setCellValue(excelReportHeaders.getFieldAssociatedToEmployee(shiftDetails));
            }
        }

        return this;
    }

    public ExcelReportService autoSizeColumns() {
        XSSFSheet sheet = workbook.getSheet(SHEET_NAME);
        IntStream.range(0, PAGE_HEADERS.size()).forEach(sheet::autoSizeColumn);
        return this;
    }

    public ByteArrayResource toFile() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            return new ByteArrayResource(bos.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

}