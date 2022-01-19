package com.example.courseprogectbackend.service;

import com.example.courseprogectbackend.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
}
