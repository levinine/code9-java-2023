package com.levi9.code9.monolithic.messaging;

import com.levi9.code9.monolithic.user.User;
import com.levi9.code9.monolithic.user.UserOperation;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@UtilityClass
@Slf4j
public class MessageFactory {

    public static UserMessage createUserUpdateMessage(User user, UserOperation operation) {
        return UserMessage.builder().email(user.getEmail())
                .typeOfOperation(operation.getText())
                .version(1)
                .notificationType(NotificationType.EMAIL.name())
                .sendingTime(OffsetDateTime.now().toString())
                .build();
    }

}
