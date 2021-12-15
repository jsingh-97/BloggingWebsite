package com.DSD.HAWK.PROJECT.aggregator.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SignUpRequest {

    private String name;

    private String email;

    private String password;

    private String phone;

    private String city;

}
