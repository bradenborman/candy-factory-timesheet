package cftimesheet.controllers;

import cftimesheet.models.Employee;
import cftimesheet.models.ShiftDetails;
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

    public ApiController(EmailSendingService emailSendingService, DataRetrievalService dataRetrievalService) {
        this.emailSendingService = emailSendingService;
        this.dataRetrievalService = dataRetrievalService;
    }

    @GetMapping("/email")
    public ResponseEntity<String> sendTestEmail() {
        emailSendingService.sendTestEmail();
        return ResponseEntity.ok("Email Sent");
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllActiveEmployees() {
        return ResponseEntity.ok(dataRetrievalService.getAllActiveEmployees());
    }

    @GetMapping("/shifts:today")
    public ResponseEntity<List<ShiftDetails>> fetchShiftsToday() {
        return ResponseEntity.ok(dataRetrievalService.fetchShiftsToday());
    }

}