package com.mohitul.blog_apps_demo.services;

import java.util.List;

import com.mohitul.blog_apps_demo.payloads.CategoryDto;

public interface CategoryServices {

    CategoryDto createNewCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Long CategoryId);

    CategoryDto getCategoryById(Long CategoryId);

    List<CategoryDto> listOfCategories();

    void deleteCategory(Long CategoryId);
}
