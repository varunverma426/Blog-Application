package com.blogapplication.Services.Service;

import com.blogapplication.payloads.CategoryDTO;
import com.blogapplication.payloads.PaginitationResponse;

public interface CategoryServices {

    //create
     CategoryDTO createCategory(CategoryDTO categoryDto);
    //update
     CategoryDTO updateCategory(CategoryDTO categoryDto,Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //getAll
    PaginitationResponse getAllCategory(int pageNumber, int pageSize,String sortBy,String sortDir);
    //getById
    CategoryDTO getCategoryById(Integer categoryId);
}
