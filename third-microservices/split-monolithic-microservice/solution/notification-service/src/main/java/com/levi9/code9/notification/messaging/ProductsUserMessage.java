package com.levi9.code9.notification.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.levi9.code9.notification.messaging.BaseMessage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder
public class ProductsUserMessage extends BaseMessage {

    @JsonProperty("email")
    String email;

    @JsonProperty("products")
    List<String> products;

    @JsonProperty("notification_type")
    String notificationType;

}
