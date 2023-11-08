package com.blogapplication.Services.Impl;

import com.blogapplication.Entities.CategoryEntity;
import com.blogapplication.Exceptions.ResourceNotFound;
import com.blogapplication.Repositories.CategoryDAO;
import com.blogapplication.Services.Service.CategoryServices;
import com.blogapplication.payloads.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryServices {

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {
       CategoryEntity categoryent= this.modelMapper.map(categoryDto, CategoryEntity.class);
       CategoryEntity addedCat=this.categoryDAO.save(categoryent);
        return this.modelMapper.map(addedCat,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId) {

        CategoryEntity categoryData= categoryDAO.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category","categoryId",categoryId));
        categoryData.setCategoryTitle(categoryDto.getCategoryTitle());
        categoryData.setCategoryDescription(categoryDto.getCategoryDescription());
        CategoryEntity updatedCategory=this.categoryDAO.save(categoryData);
        return this.modelMapper.map(updatedCategory,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        CategoryEntity categoryData=this.categoryDAO.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category","CategoryId",categoryId));
        this.categoryDAO.delete(categoryData);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryEntity> categoryentity=this.categoryDAO.findAll();
        List<CategoryDTO> categoryDTO=categoryentity.stream().map(data -> modelMapper.map(data,CategoryDTO.class)).collect(Collectors.toList());
        return categoryDTO;
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        CategoryEntity categoryData=this.categoryDAO.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category","CategoryId",categoryId));
        return this.modelMapper.map(categoryData,CategoryDTO.class);
    }
}
