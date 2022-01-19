package com.example.courseprogectbackend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import  java.util.Set;

@Data
public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Set<String> roles;
}
