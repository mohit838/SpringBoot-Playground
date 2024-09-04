package com.mohitul.blog_apps_demo.payloads;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String postTitle;
    private String postDesc;
    private String postImageName;
    private Date postDate;
    private CategoryDto category;
    private UserDto user;
}
