package com.levi9.code9.monolithic.user.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

    Long id;
    String email;
    String password;
    boolean receiveUpdate;
}
