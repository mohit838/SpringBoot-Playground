package com.mohitul.blog_apps_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohitul.blog_apps_demo.entity.CommentsEntity;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

}
