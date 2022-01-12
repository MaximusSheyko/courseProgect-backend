package com.example.courseprogectbackend.repository;

import com.example.courseprogectbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByRoles_NameContains(String roleName);
}
