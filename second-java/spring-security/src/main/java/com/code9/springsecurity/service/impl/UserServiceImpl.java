package com.code9.springsecurity.service.impl;

import com.code9.springsecurity.repository.UserRepository;
import com.code9.springsecurity.model.User;
import com.code9.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return userRepository.findByEmail(email);
    }
}
