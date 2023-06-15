package com.levi9.code9.users.messaging;

import com.levi9.code9.users.user.User;
import com.levi9.code9.users.user.UserOperation;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@UtilityClass
@Slf4j
public class MessageFactory {

    public static UserUpdateMessage createUserUpdateMessage(User user, UserOperation operation) {
        return UserUpdateMessage.builder().email(user.getEmail())
                .typeOfOperation(operation.getText())
                .version(1)
                .notificationType(NotificationType.EMAIL.name())
                .sendingTime(OffsetDateTime.now().toString())
                .build();
    }

}
