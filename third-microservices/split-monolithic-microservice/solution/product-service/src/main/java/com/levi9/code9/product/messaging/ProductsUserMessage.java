package com.levi9.code9.product.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.levi9.code9.users.messaging.BaseMessage;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class ProductsUserMessage extends BaseMessage{

    @JsonProperty("email")
    String email;

    @JsonProperty("products")
    List<String> products;

    @JsonProperty("notification_type")
    String notificationType;

}
