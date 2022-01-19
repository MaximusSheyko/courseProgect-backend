package com.example.courseprogectbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserProfileDto {
    private Long id;

    private String username;

    private List<String> roles;
}
