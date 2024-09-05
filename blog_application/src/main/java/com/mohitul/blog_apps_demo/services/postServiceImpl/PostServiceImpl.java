package com.mohitul.blog_apps_demo.services.postServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mohitul.blog_apps_demo.apiResponse.PostResponse;
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
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        // Find the category
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", categoryId));

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
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        post.setPostTitle(postDto.getPostTitle());
        post.setPostDesc(postDto.getPostDesc());
        post.setPostImageName(postDto.getPostImageName());

        PostEntity updatePost = postRepository.save(post);
        return modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity getPostById = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return modelMapper.map(getPostById, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        // Validate pageNumber to ensure it is not negative
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number must be greater than or equal to 0.");
        }

        // Validate pageSize to ensure it is positive
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0.");
        }

        // Default to ascending order if sortDir is invalid
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;

        // Create a Sort object based on the provided sortBy and sortDir
        Sort sort = Sort.by(direction, sortBy);

        // Create a PageRequest with the provided pageNumber, pageSize, and sort
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Fetch the page from the repository
        Page<PostEntity> pagePost = postRepository.findAll(pageable);
        List<PostEntity> postContent = pagePost.getContent();

        // Fetch the first page content if pageNumber is 0 or 1
        if (pageNumber == 0 || pageNumber == 1) {
            PageRequest firstPageRequest = PageRequest.of(0, pageSize, sort);
            Page<PostEntity> firstPagePost = postRepository.findAll(firstPageRequest);
            postContent = firstPagePost.getContent();
        }

        // Convert entities to DTOs
        List<PostDto> postDtoList = postContent.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        // Prepare the response
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public void deletePost(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPostsByCategoryId(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<PostEntity> posts = postRepository.findByCategoryEntity(category);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        List<PostEntity> posts = postRepository.findByUserEntity(user);
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<PostEntity> posts = postRepository.searchByPostTitle("%" + keyword + "%");
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
