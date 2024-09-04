package com.mohitul.blog_apps_demo.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @Size(min = 4, message = "Must be at least 4 characters.")
    // @NotBlank(message = "User name cannot be empty.")
    private String userName;

    @Email(message = "Email not valid.")
    // @NotBlank(message = "Email cannot be empty.")
    private String email;

    // @NotBlank(message = "Password cannot be empty.")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters.")
    private String password;

    // @NotBlank(message = "About field cannot be empty.")
    private String about;
}
