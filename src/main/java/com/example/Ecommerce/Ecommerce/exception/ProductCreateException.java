package com.example.Ecommerce.Ecommerce.exception;

public class ProductCreateException extends Exception { // Extending Exception (or you can extend RuntimeException)

    public ProductCreateException(String message) {
        super(message);
    }

    public ProductCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}

