package cftimesheet.controllers;

import cftimesheet.managers.EmployeeManager;
import cftimesheet.models.Employee;
import cftimesheet.models.NewEmployeeRequest;
import cftimesheet.models.ShiftDetails;
import cftimesheet.models.UpdateEmployeeRequest;
import cftimesheet.services.DataRetrievalService;
import cftimesheet.services.EmailSendingService;
import cftimesheet.services.ExcelReportService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    public ApiController(EmailSendingService emailSendingService, EmployeeManager employeeManager,
                         DataRetrievalService dataRetrievalService) {
        this.emailSendingService = emailSendingService;
        this.employeeManager = employeeManager;
        this.dataRetrievalService = dataRetrievalService;
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

    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUser(@RequestParam String personId) {
        employeeManager.deleteUser(personId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-employee-data")
    public ResponseEntity<Void> updateEmployee(@RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        employeeManager.updateEmployee(updateEmployeeRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employee:create")
    public ResponseEntity<Void> createNewEmployee(@RequestBody NewEmployeeRequest newEmployeeRequest) {
        employeeManager.createNewEmployee(newEmployeeRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value="/download-excel-time-sheet")
    public HttpEntity<ByteArrayResource> createExcelWithTaskConfigurations() throws IOException {
        ExcelReportService excelService = new ExcelReportService();
        List<ShiftDetails> shiftsWorkedToday = dataRetrievalService.fetchShiftsToday();

        ByteArrayResource excelFile = excelService
                .setShiftsWorked(shiftsWorkedToday)
                .withStartingWorkbook()
                .withHeaders(L_NAME, F_NAME, PHONE, EMAIL, ADDRESS, VENMO, PAYPAL, CLOCK_IN, CLOCK_OUT, TOTAL_TIME_WORKED)
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
