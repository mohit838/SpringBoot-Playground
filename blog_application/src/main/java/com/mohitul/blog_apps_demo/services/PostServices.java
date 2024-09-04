package com.mohitul.blog_apps_demo.services;

import java.util.List;

import com.mohitul.blog_apps_demo.payloads.PostDto;

public interface PostServices {

    PostDto createNewPost(PostDto postDto, Long userId, Long categoryId);

    PostDto updatePost(PostDto postDto, Long postId);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPosts();

    void deletePost(Long postId);

    List<PostDto> getAllPostsByCategoryId(Long categoryId);

    List<PostDto> getAllPostsByUserId(Long userId);

    List<PostDto> searchPosts(String keyword);
}
