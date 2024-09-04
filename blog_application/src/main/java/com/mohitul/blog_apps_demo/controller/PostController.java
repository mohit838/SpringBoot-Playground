package com.mohitul.blog_apps_demo.controller;

import com.mohitul.blog_apps_demo.payloads.CategoryDto;
import com.mohitul.blog_apps_demo.payloads.PostDto;
import com.mohitul.blog_apps_demo.services.CategoryServices;
import com.mohitul.blog_apps_demo.services.PostServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostServices postServices;

    @PostMapping("/new-post/user/{user}/category/{category}/post")
    public ResponseEntity<PostDto> createNewCategory(
            @Valid @RequestBody PostDto postDto,
            @PathVariable("user") Long userId,
            @PathVariable("category") Long categoryId
            ) {
        PostDto newPost = postServices.createNewPost(postDto, userId, categoryId);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    // Get All posts by user id
    @GetMapping("/get-all-posts-user/{id}")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(
            @PathVariable("id") Long userId
    ) {
        List<PostDto> getAllPostsUser = postServices.getAllPostsByUserId(userId);
        return ResponseEntity.ok(getAllPostsUser);
    }

    // Get all posts by category id
    @GetMapping("/get-all-posts-category/{id}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(
            @PathVariable("id") Long categoryId
    ) {
        List<PostDto> getAllPostsByCat = postServices.getAllPostsByCategoryId(categoryId);
        return ResponseEntity.ok(getAllPostsByCat);
    }

    @GetMapping("/get-all-posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> getAllPosts = postServices.getAllPosts();
        return ResponseEntity.ok(getAllPosts);
    }

    @GetMapping("/get-post/{id}")
    public ResponseEntity<PostDto> getPostById(
            @PathVariable("id") Long postId
    ) {
        PostDto post = postServices.getPostById(postId);
        return ResponseEntity.ok(post);
    }
}
