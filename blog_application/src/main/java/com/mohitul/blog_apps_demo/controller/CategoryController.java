package com.mohitul.blog_apps_demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohitul.blog_apps_demo.apiResponse.ApiResponse;
import com.mohitul.blog_apps_demo.payloads.CategoryDto;
import com.mohitul.blog_apps_demo.services.CategoryServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryServices categoryServices;

    @PostMapping("/new-category")
    public ResponseEntity<CategoryDto> createNewCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto newCategory = categoryServices.createNewCategory(categoryDto);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PatchMapping("/update-category/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @Valid @RequestBody CategoryDto categoryDto,
            @PathVariable("id") Long categoryId) {
        CategoryDto updatedCategory = categoryServices.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(updatedCategory);
    }

    @GetMapping("/get-category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId) {
        CategoryDto category = categoryServices.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/get-all-category")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryServices.listOfCategories();
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/del-category/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable("id") Long categoryId) {
        categoryServices.deleteCategory(categoryId);
        ApiResponse apiResponse = new ApiResponse("Category deleted successfully.", true);
        return ResponseEntity.ok(apiResponse);
    }
}
