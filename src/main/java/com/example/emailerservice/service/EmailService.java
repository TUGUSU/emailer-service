package com.example.emailerservice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.emailerservice.dto.UploadEmailRequest;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${app.mail.from}")
    private String fromEmail;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendUploadSuccessEmail(UploadEmailRequest request) {
        String subject = "Image Upload Successful";
        String content = "Hello " + request.getUsername() + ",\n\n"
                + "Your image has been uploaded successfully.\n\n"
                + "File Name: " + request.getFileName() + "\n"
                + "File URL: " + request.getFileUrl() + "\n\n"
                + "Best regards,\nSOA Lab System";

        sendEmail(request.getTo(), subject, content);
    }

    public void sendWeeklyReminderEmail(String to, String username) {
        String subject = "Weekly Reminder";
        String content = "Hello " + username + ",\n\n"
                + "This is your weekly reminder email from the SOA Lab system.\n\n"
                + "Best regards,\nSOA Lab System";

        sendEmail(to, subject, content);
    }

    private void sendEmail(String to, String subject, String contentText) {
        String url = "https://api.sendgrid.com/v3/mail/send";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(sendGridApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "personalizations", new Object[] {
                        Map.of("to", new Object[] { Map.of("email", to) })
                },
                "from", Map.of("email", fromEmail),
                "subject", subject,
                "content", new Object[] {
                        Map.of("type", "text/plain", "value", contentText)
                });

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("SendGrid API failed: " + response.getStatusCode() + " " + response.getBody());
        }
    }
}
