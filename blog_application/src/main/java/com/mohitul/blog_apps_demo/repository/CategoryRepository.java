package com.mohitul.blog_apps_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mohitul.blog_apps_demo.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    // boolean existsByCategoryTitle(String categoryTitle);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM CategoryEntity c WHERE c.categoryTitle = :categoryTitle")
    boolean existsByCategoryTitle(@Param("categoryTitle") String categoryTitle);

}
