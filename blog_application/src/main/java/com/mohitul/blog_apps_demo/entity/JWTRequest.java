package com.mohitul.blog_apps_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JWTRequest {
    private String username;
    private String password;
    private String refreshToken;
}
