package com.DSD.HAWK.PROJECT.aggregator.Service;


import com.DSD.HAWK.PROJECT.aggregator.Request.SignUpRequest;
import com.DSD.HAWK.PROJECT.aggregator.Response.Auth.AuthResponse;

public interface AuthAggregator {
    AuthResponse getUserDetails(String email,String password);
    AuthResponse registerUser(SignUpRequest signUpRequest);
    AuthResponse deleteUser(String email);

    String confirmToken(String token);
}

