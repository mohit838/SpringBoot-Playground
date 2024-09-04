package com.mohitul.blog_apps_demo.services;

import java.util.List;

import com.mohitul.blog_apps_demo.payloads.UserDto;

public interface UserServices {

    UserDto createNewUser(UserDto user);

    UserDto updateUser(UserDto user, Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> listOfUsers();

    void deleteUser(Long userId);

}
