package cftimesheet.controllers;

import cftimesheet.managers.ShiftManager;
import cftimesheet.models.Employee;
import cftimesheet.models.ShiftDetails;
import cftimesheet.models.StartNewShiftRequest;
import cftimesheet.services.DataRetrievalService;
import cftimesheet.services.EmailSendingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    EmailSendingService emailSendingService;
    DataRetrievalService dataRetrievalService;
    ShiftManager shiftManager;

    public ApiController(EmailSendingService emailSendingService, DataRetrievalService dataRetrievalService, ShiftManager shiftManager) {
        this.emailSendingService = emailSendingService;
        this.dataRetrievalService = dataRetrievalService;
        this.shiftManager = shiftManager;
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

    @PostMapping("start-shift")
    public ResponseEntity<Void> startNewShift(@RequestBody StartNewShiftRequest request) {
        shiftManager.startNewShiftByEmployeeId(request.getPersonId(), request.getClockInTime());
        return ResponseEntity.ok().build();
    }

}