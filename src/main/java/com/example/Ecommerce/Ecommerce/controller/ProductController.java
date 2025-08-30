package com.example.Ecommerce.Ecommerce.controller;

import com.example.Ecommerce.Ecommerce.dto.ProductDto;
import com.example.Ecommerce.Ecommerce.exception.AccessDeniedCustomException;
import com.example.Ecommerce.Ecommerce.services.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-operations")
public class ProductController {

    @Autowired
    private CreateProductService productService;

    @PostMapping("/create-new-product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean Admin = auth.getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!Admin) {
            throw new AccessDeniedCustomException("Required role: ADMIN");
        }
        ProductDto newproduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newproduct);
    }

}
