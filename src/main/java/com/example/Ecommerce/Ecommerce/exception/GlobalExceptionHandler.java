package com.example.Ecommerce.Ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<Map<String, Object>> handleUserCreationException(UserCreationException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedCustomException.class)
    public ResponseEntity<Map<String, Object>> handleSpringSecurityAccessDenied(AccessDeniedCustomException ex) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", "Forbidden");
        body.put("user", auth != null ? auth.getName() : "Anonymous");
        body.put("authorities", auth != null
                ? auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                : List.of());
        body.put("message", "Required role: SUPER_ADMIN");

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ProductCategoryCreateException.class)
    public ResponseEntity<String> handleProductCategoryCreateException(ProductCategoryCreateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ProductCreateException.class)
    public ResponseEntity<String> handleProductCreateException(ProductCreateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
