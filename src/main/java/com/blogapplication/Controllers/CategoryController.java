package com.blogapplication.Controllers;

import com.blogapplication.Services.Service.CategoryServices;
import com.blogapplication.Utils.ApiResponse;
import com.blogapplication.config.AppConstant;
import com.blogapplication.payloads.CategoryDTO;
import com.blogapplication.payloads.PaginitationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    //create
    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categoryoutput = this.categoryServices.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryoutput, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId) {
        CategoryDTO updatedOutput = this.categoryServices.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(updatedOutput, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        this.categoryServices.deleteCategory(categoryId);
        return new ResponseEntity(new ApiResponse("Category deleted sucessfully", true), HttpStatus.OK);
    }

    //getAll
    @GetMapping("/getAllCategory")
    public ResponseEntity<PaginitationResponse> getAllCategory(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNumber,
                                                               @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) int pageSize,
                                                               @RequestParam(value = "sortBy", defaultValue = "categoryTitle", required = false) String sortBy,
                                                               @RequestParam(value = "sortDir", defaultValue = AppConstant.PAGE_DIR, required = false) String sortDir) {
        return new ResponseEntity<PaginitationResponse>(this.categoryServices.getAllCategory(pageNumber, pageSize,sortBy,sortDir), HttpStatus.OK);
    }

    //getById
    @GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(this.categoryServices.getCategoryById(categoryId), HttpStatus.OK);
    }
}
