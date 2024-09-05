package com.mohitul.blog_apps_demo.services.commentsServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mohitul.blog_apps_demo.entity.CommentsEntity;
import com.mohitul.blog_apps_demo.entity.PostEntity;
import com.mohitul.blog_apps_demo.exceptions.ResourceNotFoundException;
import com.mohitul.blog_apps_demo.payloads.CommentsDto;
import com.mohitul.blog_apps_demo.repository.CommentsRepository;
import com.mohitul.blog_apps_demo.repository.PostRepository;
import com.mohitul.blog_apps_demo.services.CommentServices;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentServicesImpl implements CommentServices {

    private CommentsRepository commentsRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Override
    public CommentsDto createNewComments(CommentsDto commentsDto, Long postId) {

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        CommentsEntity commentsEntity = modelMapper.map(commentsDto, CommentsEntity.class);

        commentsEntity.setPost(postEntity);

        CommentsEntity savedNewComment = commentsRepository.save(commentsEntity);
        return modelMapper.map(savedNewComment, CommentsDto.class);
    }

    @Override
    public void deleteComments(Long commentId) {
        CommentsEntity comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentId));
        commentsRepository.delete(comment);
    }

}
