package com.levi9.code9.notification.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
public class UserMessage extends BaseMessage {

    @JsonProperty("email")
    String email;

    @JsonProperty("type_of_operation")
    String typeOfOperation;

    @JsonProperty("notification_type")
    String notificationType;

}
