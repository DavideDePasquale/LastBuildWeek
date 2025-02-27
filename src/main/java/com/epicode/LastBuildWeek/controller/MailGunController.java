package com.epicode.LastBuildWeek.controller;

import com.epicode.LastBuildWeek.service.MailGunService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/email")
public class MailGunController {

    private final MailGunService mailgunService;

    public MailGunController(MailGunService mailgunService) {
        this.mailgunService = mailgunService;
    }

    @PostMapping("/send")
    public String sendTestEmail(@RequestParam String to,
                                @RequestParam String subject,
                                @RequestParam String text) {
        try {
            mailgunService.sendEmail(to, subject, text);
            return "Email sent successfully!";
        } catch (IOException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}
