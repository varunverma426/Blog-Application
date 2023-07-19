package com.blogapplication.Exceptions;

import com.blogapplication.Utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFound ex){
      String message=ex.getMessage();
      ApiResponse apiResponse=new ApiResponse(message,false);
      return  new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
