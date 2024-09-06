package com.mohitul.blog_apps_demo.services.categoryServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mohitul.blog_apps_demo.entity.CategoryEntity;
import com.mohitul.blog_apps_demo.exceptions.ResourceAlreadyExistsException;
import com.mohitul.blog_apps_demo.exceptions.ResourceNotFoundException;
import com.mohitul.blog_apps_demo.payloads.CategoryDto;
import com.mohitul.blog_apps_demo.repository.CategoryRepository;
import com.mohitul.blog_apps_demo.services.CategoryServices;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryServices {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto createNewCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByCategoryTitle(categoryDto.getCategoryTitle())) {
            throw new ResourceAlreadyExistsException("Category", "title", categoryDto.getCategoryTitle());
        }

        CategoryEntity categoryEntity = modelMapper.map(categoryDto, CategoryEntity.class);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        CategoryEntity existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        if (categoryDto.getCategoryTitle() != null && !categoryDto.getCategoryTitle().trim().isEmpty()) {
            existingCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        }
        if (categoryDto.getCategoryDesc() != null && !categoryDto.getCategoryDesc().trim().isEmpty()) {
            existingCategory.setCategoryDesc(categoryDto.getCategoryDesc());
        }

        CategoryEntity updatedCategory = categoryRepository.save(existingCategory);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return modelMapper.map(categoryEntity, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> listOfCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "categoryId"));
        return categories.stream()
                .map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(categoryEntity);
    }
}
