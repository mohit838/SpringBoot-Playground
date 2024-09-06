package com.mohitul.blog_apps_demo.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        if (!response.isCommitted()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json"); // Set content type to JSON

            PrintWriter writer = response.getWriter();
            // Constructing JSON response body
            String jsonResponse = String
                    .format("{\"error\": \"Access Denied\", \"message\": \"%s\"}",
                            authException.getMessage());
            writer.println(jsonResponse);
        }
    }
}
