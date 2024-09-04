package com.mohitul.blog_apps_demo.payloads;

import com.mohitul.blog_apps_demo.entity.CategoryEntity;
import com.mohitul.blog_apps_demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private String postTitle;

    private String postDesc;

    private String postImageName;

    private Date postDate;

    private CategoryDto category;

    private UserDto user;
}
