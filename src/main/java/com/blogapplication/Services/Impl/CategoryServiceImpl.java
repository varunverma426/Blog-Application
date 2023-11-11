package com.blogapplication.Services.Impl;

import com.blogapplication.Entities.CategoryEntity;
import com.blogapplication.Exceptions.ResourceNotFound;
import com.blogapplication.Repositories.CategoryDAO;
import com.blogapplication.Services.Service.CategoryServices;
import com.blogapplication.payloads.CategoryDTO;
import com.blogapplication.payloads.PaginitationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PaginitationResponse getAllCategory(int pageNumber,int pageSize,String sortBy,String sortDir) {

        //introducing Pageinitiation concept

        //Create Sorting object and doing sorting according to sort direction
        Sort sorting = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        //creating pagable object
        Pageable page= PageRequest.of(pageNumber,pageSize,sorting);
        Page<CategoryEntity> pageCategory=this.categoryDAO.findAll(page);
        List<CategoryEntity> categoryEntities= pageCategory.getContent();
        List<CategoryDTO> categoryDTOS=categoryEntities.stream().map(p->this.modelMapper.map(p,CategoryDTO.class)).collect(Collectors.toList());

        PaginitationResponse PaginitationResponse=new PaginitationResponse();
        PaginitationResponse.setContentCategory(categoryDTOS);
        PaginitationResponse.setPageNumber(pageCategory.getNumber());
        PaginitationResponse.setPageSize(pageCategory.getSize());
        PaginitationResponse.setTotalElement(pageCategory.getTotalElements());
        PaginitationResponse.setTotalPages(pageCategory.getTotalPages());
        PaginitationResponse.setLastPage(pageCategory.isLast());
        return PaginitationResponse;
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        CategoryEntity categoryData=this.categoryDAO.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category","CategoryId",categoryId));
        return this.modelMapper.map(categoryData,CategoryDTO.class);
    }
}
