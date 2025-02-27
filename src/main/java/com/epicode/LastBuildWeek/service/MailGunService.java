package com.epicode.LastBuildWeek.service;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailGunService {


    @Value("${mailgun.api.key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;

    @Value("${mailgun.sender}")
    private String senderEmail;

    private final OkHttpClient client = new OkHttpClient();

    public void sendEmail(String to, String subject, String text) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("from", senderEmail)
                .add("to", to)
                .add("subject", subject)
                .add("text", text)
                .build();

        Request request = new Request.Builder()
                .url("https://api.mailgun.net/v3/" + domain + "/messages")
                .addHeader("Authorization", Credentials.basic("api", apiKey))
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            System.out.println("Email sent successfully: " + response.body().string());
        }
    }
}
