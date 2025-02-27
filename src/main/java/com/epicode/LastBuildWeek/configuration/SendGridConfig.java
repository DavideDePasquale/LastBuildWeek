package com.epicode.LastBuildWeek.configuration;


import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    // impostare la costante proveniente dal file properties
   @Value("${SENDGRID_API_KEY}") String sendGridKey;

    @Bean
    public SendGrid configurazioneMail(){
        return new SendGrid(sendGridKey);
    }
}
