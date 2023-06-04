package com.levi9.code9.product.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public abstract class BaseMessage {

    @JsonProperty("version")
    public long version;

    @JsonProperty("sending_time")
    public String sendingTime;
}
