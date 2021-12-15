package com.DSD.HAWK.PROJECT.aggregator.Response.Auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class AuthResponse {
    private String username;
    private String email;
    private String designation;
    private String city;
    private Boolean isLogged;
    private String phone;
    private String text;
    public AuthResponse() {
    }
    public AuthResponse(String text) {
        this.text=text;
    }
}
