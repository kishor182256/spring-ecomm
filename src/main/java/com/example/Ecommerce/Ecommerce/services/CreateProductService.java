package com.example.Ecommerce.Ecommerce.services;

import com.example.Ecommerce.Ecommerce.dto.ProductCategoryDto;
import com.example.Ecommerce.Ecommerce.dto.ProductDto;

public interface CreateProductService {

    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productCategoryDto);
    void deleteProduct(String categoryId);

    ProductDto getSingleProduct(String categoryId);

}
