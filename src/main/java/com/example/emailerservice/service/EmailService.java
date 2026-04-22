package com.example.emailerservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.emailerservice.dto.UploadEmailRequest;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendUploadSuccessEmail(UploadEmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(request.getTo());
        message.setSubject("Image Upload Successful");
        message.setText(
            "Hello " + request.getUsername() + ",\n\n"
            + "Your image has been uploaded successfully.\n\n"
            + "File Name: " + request.getFileName() + "\n"
            + "File URL: " + request.getFileUrl() + "\n\n"
            + "Best regards,\nSOA Lab System"
        );

        mailSender.send(message);
    }

    public void sendWeeklyReminderEmail(String to, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("Weekly Reminder");
        message.setText(
            "Hello " + username + ",\n\n"
            + "This is your weekly reminder email from the SOA Lab system.\n\n"
            + "Best regards,\nSOA Lab System"
        );

        mailSender.send(message);
    }
}
