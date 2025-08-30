package com.example.Ecommerce.Ecommerce.exception;

public class ProductCategoryCreateException extends RuntimeException{

    public ProductCategoryCreateException(String message) {
        super(message);
    }

    public ProductCategoryCreateException(String message, Throwable cause) {
        super(message, cause);
    }

}
