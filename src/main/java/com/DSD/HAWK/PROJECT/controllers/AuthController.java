package com.DSD.HAWK.PROJECT.controllers;


import com.DSD.HAWK.PROJECT.aggregator.Request.SignUpRequest;
import com.DSD.HAWK.PROJECT.aggregator.Response.Auth.AuthResponse;
import com.DSD.HAWK.PROJECT.aggregator.Service.AuthAggregator;
import com.DSD.HAWK.PROJECT.model.ConfirmationToken;
import com.DSD.HAWK.PROJECT.model.UserData;
import com.DSD.HAWK.PROJECT.repository.ConfirmationTokenRepository;
import com.DSD.HAWK.PROJECT.repository.Service.ConfirmationTokenService;
import com.DSD.HAWK.PROJECT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthAggregator authAggregator;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @GetMapping("/")
    public String healthCheck(){
        return "Welcome to Blog";
    }

    @GetMapping("/auth/user")
    public AuthResponse getUserDetails(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password){
        return authAggregator.getUserDetails(email,password);
    }

    @PostMapping("/auth/user")
    public AuthResponse registerUser(
            @RequestBody SignUpRequest signUpRequest){
        return authAggregator.registerUser(signUpRequest);
    }
    @DeleteMapping("/auth/user")
    public AuthResponse registerUser(
            @RequestParam(value = "email", required = true) String email){
        return authAggregator.deleteUser(email);
    }
    @GetMapping("/auth/user/confirmToken")
    public String confirmToken(@RequestParam("token") String token){
        return authAggregator.confirmToken(token);
    }
}

