package com.mohitul.blog_apps_demo.mapper;

import com.mohitul.blog_apps_demo.entity.UserEntity;
import com.mohitul.blog_apps_demo.payloads.UserDto;

public class UserMapper {
    public static UserDto mapToUserDto(UserEntity userEntity) {
        return new UserDto(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getAbout());
    }

    public static UserEntity mapToUser(UserDto userDto) {
        return new UserEntity(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getAbout());
    }

}
