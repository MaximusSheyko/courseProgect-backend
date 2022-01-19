package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByRoles_NameContains(String roleName);

    Optional<User> findUserByName(String name);

    boolean existsUserByName(String name);
}
