package com.levi9.code9.notification.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendMailReport(String type, String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("code9levi9@gmail.com");
        message.setTo(to);
        message.setSubject(String.format("%s report - WebShop", type));
        message.setText(text);

        mailSender.send(message);
    }

    public void sendMailUserUpdate(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(String.format("Profile information: %s", to));
        message.setText(text);

        mailSender.send(message);
    }

    public String generateUserUpdateText(String email, String type) {
        return String.format("Dear user  '%s', \n" +
                "Your profile data is '%s'" +
                "\n\nSincerely, \nWebShop", email, type);
    }


    public String generateReportText(List<String> products) {
        StringBuilder text = new StringBuilder("Products: \n" +
                "-------------------\n");

        for (String product : products) {
            text.append(String.format("* Product: %s\n", product));
        }
        text.append("\n\nSincerely, \nWebShop");
        return text.toString();
    }
}
