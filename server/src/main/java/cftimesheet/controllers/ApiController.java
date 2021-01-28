package cftimesheet.controllers;

import cftimesheet.managers.EmployeeManager;
import cftimesheet.managers.ShiftManager;
import cftimesheet.models.ChangeShiftRequest;
import cftimesheet.models.Employee;
import cftimesheet.models.NewEmployeeRequest;
import cftimesheet.models.ShiftDetails;
import cftimesheet.services.DataRetrievalService;
import cftimesheet.services.EmailSendingService;
import cftimesheet.services.ExcelReportService;
import cftimesheet.validators.ValidationRouter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static cftimesheet.models.ExcelReportHeaders.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    EmailSendingService emailSendingService;
    EmployeeManager employeeManager;
    DataRetrievalService dataRetrievalService;
    ShiftManager shiftManager;
    ValidationRouter validationRouter;

    public ApiController(EmailSendingService emailSendingService, EmployeeManager employeeManager,
                         DataRetrievalService dataRetrievalService, ShiftManager shiftManager, ValidationRouter validationRouter) {
        this.emailSendingService = emailSendingService;
        this.employeeManager = employeeManager;
        this.dataRetrievalService = dataRetrievalService;
        this.shiftManager = shiftManager;
        this.validationRouter = validationRouter;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validationRouter);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllActiveEmployees() {
        List<Employee> results = dataRetrievalService.getAllActiveEmployees();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/shifts:today")
    public ResponseEntity<List<ShiftDetails>> fetchShiftsToday() {
        return ResponseEntity.ok(dataRetrievalService.fetchShiftsToday());
    }

    @GetMapping("employee-data/{employeeId}")
    public ResponseEntity<Employee> getEmployeeData(@PathVariable String employeeId) {
        return ResponseEntity.ok(employeeManager.getEmployeeData(employeeId));
    }

    @PostMapping("/employee:create")
    public ResponseEntity<Void> createNewEmployee(@Validated @RequestBody NewEmployeeRequest newEmployeeRequest) {
        employeeManager.createNewEmployee(newEmployeeRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("shift-action")
    public ResponseEntity<Void> newShiftAction(@Validated @RequestBody ChangeShiftRequest request) {
        shiftManager.newShiftAction(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/shift/{empId}/{shiftId}")
    public ResponseEntity<Void> deleteShift(@PathVariable String empId, @PathVariable String shiftId) {
        shiftManager.deleteShift(shiftId, empId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value="/download-excel-time-sheet")
    public HttpEntity<ByteArrayResource> createExcelWithTaskConfigurations() throws IOException {
        ExcelReportService excelService = new ExcelReportService();
        List<ShiftDetails> shiftsWorkedToday = dataRetrievalService.fetchShiftsToday();

        ByteArrayResource excelFile = excelService
                .setShiftsWorked(shiftsWorkedToday)
                .withStartingWorkbook()
                .withHeaders(NAME, PHONE, EMAIL, ADDRESS, CLOCK_IN, CLOCK_OUT, TOTAL_TIME_WORKED)
                .createRowsFromShiftsWorked()
                .autoSizeColumns()
                .toFile();

        HttpHeaders header = new HttpHeaders();
        String date = LocalDate.now().toString().toLowerCase(Locale.ROOT);
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + date + "-timesheet.xlsx");
        return new HttpEntity<>(excelFile, header);
    }

}
