package com.mohitul.blog_apps_demo.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTRefreshTokenRequest {

    @NotEmpty(message = "Refresh token must not be empty")
    private String refreshToken;
}
