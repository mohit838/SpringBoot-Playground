package com.mohitul.blog_apps_demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohitul.blog_apps_demo.entity.JWTLoginRequest;
import com.mohitul.blog_apps_demo.entity.JWTRefreshTokenRequest;
import com.mohitul.blog_apps_demo.entity.JWTResponse;
import com.mohitul.blog_apps_demo.security.JWTHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager manager;
    private final JWTHelper helper;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserDetailsService userDetailsService, AuthenticationManager manager, JWTHelper helper) {
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.helper = helper;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@Valid @RequestBody JWTLoginRequest request) {
        authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        String refreshToken = this.helper.generateRefreshToken(userDetails);

        JWTResponse response = JWTResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .username(userDetails.getUsername())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTResponse> refresh(
            @Valid @RequestBody JWTRefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        String username;
        try {
            username = helper.getUsernameFromToken(refreshToken);
        } catch (Exception e) {
            logger.error("Invalid Refresh Token", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JWTResponse(null, null, null));
        }

        if (username != null && !helper.isTokenExpired(refreshToken)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String token = this.helper.generateToken(userDetails);
            String newRefreshToken = this.helper.generateRefreshToken(userDetails);

            JWTResponse response = JWTResponse.builder()
                    .accessToken(token)
                    .refreshToken(newRefreshToken)
                    .username(userDetails.getUsername())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            logger.error("Refresh token expired or invalid for user: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JWTResponse(null, null, null));
        }
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            logger.error("Invalid Username or Password for user: {}", username);
            throw new BadCredentialsException("Invalid Username or Password!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> exceptionHandler(BadCredentialsException e) {
        return new ResponseEntity<>("Credentials Invalid!", HttpStatus.UNAUTHORIZED);
    }
}
