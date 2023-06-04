package com.levi9.code9.users.user.client;

import com.levi9.code9.users.dto.UserDto;
import feign.RequestLine;

import java.util.List;


public interface UserClient {
    @RequestLine("GET /api/users/subscribed")
    List<UserDto> getAllUsersWhichAreSubscribed();

}
