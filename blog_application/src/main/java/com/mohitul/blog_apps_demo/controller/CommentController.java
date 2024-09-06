package com.mohitul.blog_apps_demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohitul.blog_apps_demo.apiResponse.ApiResponse;
import com.mohitul.blog_apps_demo.payloads.CommentsDto;
import com.mohitul.blog_apps_demo.services.CommentServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentServices commentServices;

    @PostMapping("/new-comment/{id}")
    public ResponseEntity<CommentsDto> createNewCategory(
            @Valid @RequestBody CommentsDto commentsDto,
            @PathVariable("id") Long postId) {
        CommentsDto newComment = commentServices.createNewComment(commentsDto, postId);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/del-comment/{id}")
    public ResponseEntity<ApiResponse> deleteComments(
            @PathVariable("id") Long commentId) {
        commentServices.deleteComments(commentId);
        ApiResponse apiResponse = new ApiResponse(
                "Delete Comments Successfully.", true);
        return ResponseEntity.ok(apiResponse);
    }

}
