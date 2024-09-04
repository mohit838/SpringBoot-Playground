package com.mohitul.blog_apps_demo.controller;

import java.util.List;

import com.mohitul.blog_apps_demo.apiResponse.PostResponse;
import com.mohitul.blog_apps_demo.config.AppConstants;
import com.mohitul.blog_apps_demo.payloads.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohitul.blog_apps_demo.apiResponse.ApiResponse;
import com.mohitul.blog_apps_demo.payloads.PostDto;
import com.mohitul.blog_apps_demo.services.PostServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostServices postServices;

    @PostMapping("/new-post/user/{user}/category/{category}/post")
    public ResponseEntity<PostDto> createNewCategory(
            @Valid @RequestBody PostDto postDto,
            @PathVariable("user") Long userId,
            @PathVariable("category") Long categoryId) {
        PostDto newPost = postServices.createNewPost(postDto, userId, categoryId);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    // Get All posts by user id
    @GetMapping("/get-all-posts-user/{id}")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(
            @PathVariable("id") Long userId) {
        List<PostDto> getAllPostsUser = postServices.getAllPostsByUserId(userId);
        return ResponseEntity.ok(getAllPostsUser);
    }

    // Get all posts by category id
    @GetMapping("/get-all-posts-category/{id}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(
            @PathVariable("id") Long categoryId) {
        List<PostDto> getAllPostsByCat = postServices.getAllPostsByCategoryId(categoryId);
        return ResponseEntity.ok(getAllPostsByCat);
    }

    @GetMapping("/get-all-posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ) {
        PostResponse getAllPosts = postServices.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(getAllPosts);
    }

    @GetMapping("/get-post/{id}")
    public ResponseEntity<PostDto> getPostById(
            @PathVariable("id") Long postId) {
        PostDto post = postServices.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/del-post/{id}")
    public ResponseEntity<ApiResponse> deletePost(
            @PathVariable("id") Long postId) {
        postServices.deletePost(postId);
        ApiResponse apiResponse = new ApiResponse("Delete Post Successfully.", true);
        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/update-post/{id}")
    public ResponseEntity<PostDto> getPostById(
            @Valid @RequestBody PostDto postDto,
            @PathVariable("id") Long postId) {
        PostDto updatePost = postServices.updatePost(postDto, postId);
        // return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
        return ResponseEntity.ok(updatePost);
    }

    //  Post searches by post title
    @GetMapping("/search-by-title/{title}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("title") String keyword) {
        List<PostDto> postLists = postServices.searchPosts(keyword);
        return ResponseEntity.ok(postLists);
    }

}
