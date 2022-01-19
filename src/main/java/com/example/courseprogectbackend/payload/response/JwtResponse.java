package com.example.courseprogectbackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String token;

    private final String type = "Bearer";

    private Long id;

    private String username;

    private List<String> roles;
}
