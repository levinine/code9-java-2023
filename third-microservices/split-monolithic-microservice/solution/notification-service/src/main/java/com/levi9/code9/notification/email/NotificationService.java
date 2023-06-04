package com.levi9.code9.notification.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    public void sendUserUpdate(String email, String operation) {
        String message = emailService.generateUserUpdateText(email, operation);
        log.info(message);
        emailService.sendMailUserUpdate(email, message);
    }

    public void sendReportMailForUser(String type, String email, List<String> receipts) {
        String message = emailService.generateReportText(receipts);
        emailService.sendMailReport(type, email, message);
    }



}
