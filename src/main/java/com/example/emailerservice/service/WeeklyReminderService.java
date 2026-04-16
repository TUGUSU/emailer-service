package com.example.emailerservice.service;

import org.springframework.stereotype.Service;

@Service
public class WeeklyReminderService {

	private final EmailService emailService;

	public WeeklyReminderService(EmailService emailService) {
		this.emailService = emailService;
	}

	// Final version for weekly execution:
	// @Scheduled(cron = "0 0 9 * * MON")

	// Temporary test version: every 2 minutes
	// @Scheduled(cron = "0 */2 * * * *")
	public void sendWeeklyReminder() {
		String demoEmail = "tugusu3@gmail.com";
		String demoUsername = "Tugusu";

		emailService.sendWeeklyReminderEmail(demoEmail, demoUsername);
		System.out.println("Weekly reminder email sent to " + demoEmail);
	}
}