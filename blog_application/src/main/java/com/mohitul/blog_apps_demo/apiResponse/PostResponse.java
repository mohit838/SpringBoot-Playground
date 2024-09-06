package com.mohitul.blog_apps_demo.apiResponse;

import java.util.List;

import com.mohitul.blog_apps_demo.payloads.PostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;
}
