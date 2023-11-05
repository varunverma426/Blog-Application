package com.blogapplication.Controllers;

import com.blogapplication.Services.CategoryServices;
import com.blogapplication.Utils.ApiResponse;
import com.blogapplication.payloads.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;
    //create
    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryoutput=this.categoryServices.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryoutput, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Integer categoryId){
        CategoryDTO updatedOutput=this.categoryServices.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(updatedOutput, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryServices.deleteCategory(categoryId);
         return new ResponseEntity(new ApiResponse("Category deleted sucessfully",true),HttpStatus.OK);
    }
    //getAll
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        return new ResponseEntity<>(this.categoryServices.getAllCategory(),HttpStatus.OK);
    }
    //getById
    @GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId){
        return new ResponseEntity<>(this.categoryServices.getCategoryById(categoryId),HttpStatus.OK);
    }
}