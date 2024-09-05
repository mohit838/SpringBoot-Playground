package com.mohitul.blog_apps_demo.config;

import com.mohitul.blog_apps_demo.entity.JWTRequest;
import com.mohitul.blog_apps_demo.entity.JWTResponse;
import com.mohitul.blog_apps_demo.security.JWTHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
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
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request) {
        authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        String refreshToken = this.helper.generateRefreshToken(userDetails);

        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .refreshToken(refreshToken)
                .username(userDetails.getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTResponse> refresh(@RequestBody JWTRequest request) {
        String refreshToken = request.getRefreshToken();
        String username = helper.getUsernameFromToken(refreshToken);

        if (username != null && !helper.isTokenExpired(refreshToken)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String token = this.helper.generateToken(userDetails);
            String newRefreshToken = this.helper.generateRefreshToken(userDetails);

            JWTResponse response = JWTResponse.builder()
                    .jwtToken(token)
                    .refreshToken(newRefreshToken)
                    .username(userDetails.getUsername())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new JWTResponse(null, null, null),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> exceptionHandler(BadCredentialsException e) {
        return new ResponseEntity<>("Credentials Invalid!", HttpStatus.UNAUTHORIZED);
    }
}
