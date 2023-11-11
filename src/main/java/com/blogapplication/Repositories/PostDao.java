package com.blogapplication.Repositories;

import com.blogapplication.Entities.CategoryEntity;
import com.blogapplication.Entities.PostEntity;
import com.blogapplication.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostDao extends JpaRepository<PostEntity, Integer> {

    //custom methods
    List<PostEntity> findByUser(UserEntity user);

    List<PostEntity> findByCategory(CategoryEntity categoryEntity);

    //List<PostEntity> findByTitleContaining(String title);
}
