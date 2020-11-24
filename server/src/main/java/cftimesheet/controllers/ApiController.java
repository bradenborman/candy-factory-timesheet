package cftimesheet.controllers;

import cftimesheet.managers.EmployeeManager;
import cftimesheet.managers.ShiftManager;
import cftimesheet.models.Employee;
import cftimesheet.models.NewEmployeeRequest;
import cftimesheet.models.ShiftDetails;
import cftimesheet.models.ChangeShiftRequest;
import cftimesheet.services.DataRetrievalService;
import cftimesheet.services.EmailSendingService;
import cftimesheet.validators.ValidationRouter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/email")
    public ResponseEntity<String> sendTestEmail() {
        emailSendingService.sendTestEmail();
        return ResponseEntity.ok("Email Sent");
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

}
