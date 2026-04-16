package com.example.emailerservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.emailerservice.dto.UploadEmailRequest;
import com.example.emailerservice.service.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailController {

	private final EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/send-upload-success")
	public ResponseEntity<String> sendUploadSuccessEmail(@RequestBody UploadEmailRequest request) {
		emailService.sendUploadSuccessEmail(request);
		return ResponseEntity.ok("Upload success email sent.");
	}

	@PostMapping("/send-weekly-reminder")
	public ResponseEntity<String> sendWeeklyReminder(@RequestParam String to, @RequestParam String username) {
		emailService.sendWeeklyReminderEmail(to, username);
		return ResponseEntity.ok("Weekly reminder email sent.");
	}
}