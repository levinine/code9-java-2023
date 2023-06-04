package com.levi9.code9.notification.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.amqp.core.Message;

@Setter
@Getter
@SuperBuilder
public abstract class BaseMessage extends Message {

    @JsonProperty("version")
    public long version;

    @JsonProperty("sending_time")
    public String sendingTime;
}
