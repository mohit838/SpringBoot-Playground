package com.mohitul.blog_apps_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 100)
    @Size(min = 4, message = "Must be at least 4 characters.")
    @NotEmpty
    private String userName;

    @Email(message = "Email not valid.")
    @Column(name = "email_id", nullable = false, length = 25)
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6, max = 10, message = "Password must be 6 char long.")
    private String password;

    @NotEmpty
    private String about;
}
