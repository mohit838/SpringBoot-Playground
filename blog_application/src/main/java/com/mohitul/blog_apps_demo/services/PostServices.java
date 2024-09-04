package com.mohitul.blog_apps_demo.services;

import com.mohitul.blog_apps_demo.entity.PostEntity;
import com.mohitul.blog_apps_demo.payloads.PostDto;

import java.util.List;

public interface PostServices {

    PostDto createNewPost(PostDto postDto, Long userId, Long categoryId);
    PostDto updatePost(PostDto postDto, Long postId);
    PostDto getPostById(Long postId);
    List<PostDto> getAllPosts();
    void deletePost(PostDto postDto, Long postId);
    List<PostDto> getAllPostsByCategoryId(Long categoryId);
    List<PostDto> getAllPostsByUserId(Long userId);
    List<PostDto> searchPosts(String keyword);
}
