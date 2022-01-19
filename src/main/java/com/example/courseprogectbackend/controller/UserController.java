package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.dto.UserProfileDto;
import com.example.courseprogectbackend.mapper.UserMapper;
import com.example.courseprogectbackend.model.User;
import com.example.courseprogectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping()
    public List<UserProfileDto> users() {
        return userMapper.toUsersProfileDto(userService.getAll());
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public User user(@RequestParam("id") long id) {
        if (userService.existsById(id)) {
            return userService.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping("/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserProfileDto userRole(@PathVariable("role") String role) {
        return userMapper.toDto(userService.findByRoleName(role)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK, reason = "user deletion success")
    public void delete(@RequestParam("id") long id) {
        if (userService.existsById(id)) {
            userService.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
