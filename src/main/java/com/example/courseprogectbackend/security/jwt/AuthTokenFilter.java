package com.example.courseprogectbackend.security.jwt;

import com.example.courseprogectbackend.security.implementation.SecurityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SecurityServiceImpl securityServiceImpl;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {
        try{
            var jwt = parseJwt(request);
            if (Objects.nonNull(jwt) && jwtUtils.validateToken(jwt)){
                var username = jwtUtils.getUserNameFromJwtToken(jwt);
                var userDetails = securityServiceImpl.loadUserByUsername(username);
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(buildWebAuthenticationDetailsSource(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            LOGGER.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private WebAuthenticationDetails buildWebAuthenticationDetailsSource(HttpServletRequest request){
        return new WebAuthenticationDetailsSource().buildDetails(request);
    }

    private String parseJwt(HttpServletRequest request) {
        var headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
