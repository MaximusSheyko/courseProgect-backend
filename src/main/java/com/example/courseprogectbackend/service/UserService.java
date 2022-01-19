package com.example.courseprogectbackend.service;

import com.example.courseprogectbackend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    User findById(long id);

    void save(User save);

    void deleteById(long id);

    boolean existsById(long id);

    Optional<User> findByRoleName(String role);

    Optional<User> findByName(String name);

    boolean existsByName(String name);
}
