package com.levi9.code9.users.dto;

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
