package com.example.courseprogectbackend.security.implementation;

import com.example.courseprogectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public SecurityServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public Authentication authenticate(String username,String password){
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    public void authenticateInSystem(Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found by username -> " + username));
        return UserDetailsImpl.build(user);
    }

    public UserDetailsImpl getUserDetails(Authentication authentication){
        return authentication.getPrincipal() instanceof UserDetailsImpl
            ? (UserDetailsImpl) authentication.getPrincipal() : null;
    }


}
