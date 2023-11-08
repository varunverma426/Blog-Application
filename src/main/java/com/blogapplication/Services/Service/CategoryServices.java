package com.blogapplication.Services.Service;

import com.blogapplication.payloads.CategoryDTO;

import java.util.List;

public interface CategoryServices {

    //create
     CategoryDTO createCategory(CategoryDTO categoryDto);
    //update
     CategoryDTO updateCategory(CategoryDTO categoryDto,Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //getAll
    List<CategoryDTO> getAllCategory();
    //getById
    CategoryDTO getCategoryById(Integer categoryId);
}
