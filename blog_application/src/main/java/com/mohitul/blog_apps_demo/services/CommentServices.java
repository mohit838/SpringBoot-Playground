package com.mohitul.blog_apps_demo.services;

import com.mohitul.blog_apps_demo.payloads.CommentsDto;

public interface CommentServices {

    CommentsDto createNewComment(CommentsDto commentsDto, Long postId);

    void deleteComments(Long commentId);

}
