package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.model.User;
import com.example.courseprogectbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> users() {
        return userService.getAll();
    }

    @GetMapping("/api/user/{role}")
    public User userRole(@PathVariable("role") String role) {
        return userService.findByRoleName(role)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @GetMapping("/api/users/")
    public User user(@RequestParam("id") long id) {
        if (userService.existsById(id)) {
            return userService.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @DeleteMapping("/api/users/delete")
    @ResponseStatus(value = HttpStatus.OK, reason = "user deletion success")
    public void delete(@RequestParam("id") long id) {
        if (userService.existsById(id)) {
            userService.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
