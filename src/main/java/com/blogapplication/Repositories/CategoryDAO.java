package com.blogapplication.Repositories;

import com.blogapplication.Entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryDAO extends JpaRepository<CategoryEntity,Integer > {
}
