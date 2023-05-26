package com.levi9.code9.monolithic.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    public void sendUserUpdate(String email, String type) {
        String message = emailService.generateUserUpdateText(email, type);
        emailService.sendMailUserUpdate(email, message);
    }

}
