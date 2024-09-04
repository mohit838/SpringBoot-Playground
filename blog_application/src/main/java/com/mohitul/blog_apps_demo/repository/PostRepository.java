package com.mohitul.blog_apps_demo.repository;

import com.mohitul.blog_apps_demo.entity.CategoryEntity;
import com.mohitul.blog_apps_demo.entity.PostEntity;
import com.mohitul.blog_apps_demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByUserEntity(UserEntity userEntity);

    List<PostEntity> findByCategoryEntity(CategoryEntity categoryEntity);

    @Query("select p from PostEntity p where p.postTitle like :key")
    List<PostEntity> searchByPostTitle(@Param("key") String postTitle);
}
