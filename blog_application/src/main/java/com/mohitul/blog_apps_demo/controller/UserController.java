package com.mohitul.blog_apps_demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohitul.blog_apps_demo.payloads.UserDto;
import com.mohitul.blog_apps_demo.services.UserServices;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserServices userServices;

    @PostMapping("/user")
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto userDto) {
        UserDto newUser = userServices.createNewUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
