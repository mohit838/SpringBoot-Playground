package com.mohitul.blog_apps_demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohitul.blog_apps_demo.apiResponse.ApiResponse;
import com.mohitul.blog_apps_demo.payloads.UserDto;
import com.mohitul.blog_apps_demo.services.UserServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserServices userServices;

    @PostMapping("/")
    public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto) {
        UserDto newUser = userServices.createNewUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @Valid @RequestBody UserDto userDto,
            @PathVariable("id") Long userId) {
        UserDto updatedUser = userServices.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto getUserById = userServices.getUserById(userId);
        return ResponseEntity.ok(getUserById);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userLists = userServices.listOfUsers();
        return ResponseEntity.ok(userLists);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployeeById(@PathVariable("id") Long userId) {
        userServices.deleteUser(userId);
        ApiResponse apiResponse = new ApiResponse("Delete User Successfully.", true);
        return ResponseEntity.ok(apiResponse);
    }

}