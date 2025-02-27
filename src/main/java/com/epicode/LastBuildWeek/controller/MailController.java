package com.epicode.LastBuildWeek.controller;


import com.epicode.LastBuildWeek.payload.MailModel;
import com.epicode.LastBuildWeek.service.MailService;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailService mailService;

    // Invio mail
    @PostMapping("/sendMail")
    public String inviaMail(@RequestBody MailModel mailModel) {

        // Invio il nostro modello Mail al metodo service -> Mail (sendGrid)
        try {
           return mailService.creaMail(mailModel);
        } catch (IOException e) {
           return "Problemi nell'invio della mail";
        }

    }


}
