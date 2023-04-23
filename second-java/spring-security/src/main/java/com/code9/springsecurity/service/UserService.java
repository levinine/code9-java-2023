package com.code9.springsecurity.service;

import com.code9.springsecurity.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
}
