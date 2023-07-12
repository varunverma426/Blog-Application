package com.blogapplication.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound extends RuntimeException{

     String resourceName;
     String resourceField;

      long fieldValue;

    public ResourceNotFound(String resourceName, String resourceField, long fieldValue) {
        super(String.format("%s not found with this %s: %l",resourceName,resourceField,fieldValue));
        this.resourceName = resourceName;
        this.resourceField = resourceField;
        this.fieldValue = fieldValue;
    }


}
