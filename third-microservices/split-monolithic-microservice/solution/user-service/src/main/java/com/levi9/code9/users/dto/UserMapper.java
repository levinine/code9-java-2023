package com.levi9.code9.users.dto;

import com.levi9.code9.users.user.User;
import com.levi9.code9.users.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public User dtoToEntity(UserDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .id(dto.getId())
                .password(dto.getPassword())
                .receiveUpdate(dto.isReceiveUpdate())
                .build();
    }

    @Override
    public UserDto entityToDto(User entity) {
        return UserDto.builder()
                .email(entity.getEmail())
                .id(entity.getId())
                .password(entity.getPassword())
                .receiveUpdate(entity.isReceiveUpdate())
                .build();
    }

}
