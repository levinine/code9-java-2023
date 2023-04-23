package com.code9.springsecurity.controller;

import com.code9.springsecurity.model.AuthenticationRequest;
import com.code9.springsecurity.model.AuthenticationResponse;
import com.code9.springsecurity.model.RegisterRequest;
import com.code9.springsecurity.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthenticationService userService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.userService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
