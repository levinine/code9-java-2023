package com.levi9.code9.notification.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.code9.notification.email.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class MessagesListener {

    private final NotificationService notificationService;

    @RabbitListener(queues = "user-queue")
    public void receiveUserMessage(String message) {
        UserMessage userMessage = null;
        try {
            userMessage = new ObjectMapper().readValue(message, UserMessage.class);
            log.info(userMessage.toString());
        } catch (JsonProcessingException e) {
            log.error("Error during reading message: %s", e);
            e.printStackTrace();
            return;
        }
        log.info("Message successfully received");

        if ("EMAIL".equals(userMessage.getNotificationType())) {
            notificationService.sendUserUpdate(userMessage.getEmail(), userMessage.getTypeOfOperation());
        }
    }

    @RabbitListener(queues = "products-for-user-queue")
    public void receiveProductMessage(String message) {
        ProductsUserMessage productsUserMessage = null;
        try {
            productsUserMessage = new ObjectMapper().readValue(message, ProductsUserMessage.class);
        } catch (JsonProcessingException e) {
            log.error("Error during reading message: %s", e);
            e.printStackTrace();
            return;
        }
        log.info("Message successfully received");
        if ("EMAIL".equals(productsUserMessage.getNotificationType())) {
            notificationService.sendReportMailForUser("Weekly", productsUserMessage.getEmail(), productsUserMessage.getProducts());
        }
    }


}
