package com.levi9.code9.notification.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class MessagesListener {

    @RabbitListener(queues = "user-queue")
    public void receiveUserMessage(String message) {
        UserMessage userMessage = null;
        try {
            userMessage = new ObjectMapper().readValue(message, UserMessage.class);
        } catch (JsonProcessingException e) {
            log.error("Error during reading message: %s", e);
            e.printStackTrace();
            return;
        }
        log.info("Message successfully received");

        log.info("consume the message");
    }

}
