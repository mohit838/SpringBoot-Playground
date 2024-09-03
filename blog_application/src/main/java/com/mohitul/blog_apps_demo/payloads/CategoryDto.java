package com.mohitul.blog_apps_demo.payloads;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long categoryId;

    @Size(min = 4, message = "Category name must be at least 4 characters.")
    // @NotBlank(message = "Category name cannot be empty.")
    private String categoryTitle;

    private String categoryDesc;

}
