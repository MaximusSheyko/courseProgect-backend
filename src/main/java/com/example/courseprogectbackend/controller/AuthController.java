package com.example.courseprogectbackend.controller;

import com.example.courseprogectbackend.enumeration.RoleStatus;
import com.example.courseprogectbackend.model.Role;
import com.example.courseprogectbackend.model.User;
import com.example.courseprogectbackend.payload.request.LoginRequest;
import com.example.courseprogectbackend.payload.request.SignupRequest;
import com.example.courseprogectbackend.payload.response.JwtResponse;
import com.example.courseprogectbackend.security.implementation.SecurityServiceImpl;
import com.example.courseprogectbackend.security.jwt.JwtUtils;
import com.example.courseprogectbackend.service.RoleService;
import com.example.courseprogectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final SecurityServiceImpl securityService;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(SecurityServiceImpl securityService,RoleService roleService,PasswordEncoder encoder,JwtUtils jwtUtils,UserService userService,
                          AuthenticationManager authenticationManager) {
        this.securityService = securityService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest request){
        var authentication = authenticationManager.authenticate(securityService.authenticate(request.getUsername(),
            request.getPassword()));
        securityService.authenticateInSystem(authentication);
        var jwt = jwtUtils.generateJwtToken(authentication);
        var userDetails = securityService.getUserDetails(authentication);
        var roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
    }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.OK, reason = "User registered successfully!")
    public void register(@Valid @RequestBody SignupRequest request) {
        if (userService.existsByName(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }
        var user = User.builder()
            .name(request.getUsername())
            .password(encoder.encode(request.getPassword()))
            .build();
        var rolesRequest = request.getRoles();
        var roles = new HashSet<Role>();
        updateRoles(rolesRequest, roles);
        user.setRoles(roles);
        userService.save(user);
    }

    private void updateRoles(Set<String> rolesFromRequest,Set<Role> roles){
        if (Objects.isNull(roles)){
            roles.add(getRoleWithStatus(RoleStatus.ROLE_USER));
        }else {
            rolesFromRequest.forEach(role -> {
                if (role.equals("admin")){
                    roles.add(getRoleWithStatus(RoleStatus.ROLE_ADMIN));
                }else {
                    roles.add(getRoleWithStatus(RoleStatus.ROLE_USER));
                }
            });
        }
    }

    private Role getRoleWithStatus(RoleStatus status){
        return roleService.findByName(status.name())
            .orElseThrow(() -> new RuntimeException("Error: role not found"));
    }
}