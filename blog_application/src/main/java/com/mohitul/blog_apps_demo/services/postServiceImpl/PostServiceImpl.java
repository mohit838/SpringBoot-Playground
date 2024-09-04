package com.mohitul.blog_apps_demo.services.postServiceImpl;

import com.mohitul.blog_apps_demo.entity.CategoryEntity;
import com.mohitul.blog_apps_demo.entity.PostEntity;
import com.mohitul.blog_apps_demo.entity.UserEntity;
import com.mohitul.blog_apps_demo.exceptions.ResourceNotFoundException;
import com.mohitul.blog_apps_demo.payloads.PostDto;
import com.mohitul.blog_apps_demo.repository.CategoryRepository;
import com.mohitul.blog_apps_demo.repository.PostRepository;
import com.mohitul.blog_apps_demo.repository.UserRepository;
import com.mohitul.blog_apps_demo.services.PostServices;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostServices {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto createNewPost(PostDto postDto, Long userId, Long categoryId) {
        // Find the user
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));

        // Find the category
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("User", "Id", categoryId));

        PostEntity postEntity = modelMapper.map(postDto, PostEntity.class);
        postEntity.setPostImageName("default.png");
        postEntity.setPostDate(new Date());
        postEntity.setUserEntity(user);
        postEntity.setCategoryEntity(category);

        PostEntity savedPostEntity = postRepository.save(postEntity);

        return modelMapper.map(savedPostEntity, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        return null;
    }

    @Override
    public PostDto getPostById(PostDto postDto, Long postId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    @Override
    public void deletePost(PostDto postDto, Long postId) {

    }

    @Override
    public List<PostDto> getAllPostsByCategoryId(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
        List<PostEntity> posts = postRepository.findByCategoryEntity(category);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        List<PostEntity> posts = postRepository.findByUserEntity(user);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
