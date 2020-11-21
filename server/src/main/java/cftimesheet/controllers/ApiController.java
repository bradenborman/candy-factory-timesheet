package cftimesheet.controllers;

import cftimesheet.services.EmailSendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    EmailSendingService emailSendingService;

    @GetMapping("/email")
    public ResponseEntity<String> sendTestEmail() {
        emailSendingService.sendTestEmail();
        return ResponseEntity.ok("Email Sent");
    }


}