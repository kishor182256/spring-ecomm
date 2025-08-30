package com.example.Ecommerce.Ecommerce.services;

import com.example.Ecommerce.Ecommerce.dto.ProductCategoryDto;

public interface CreateCategoryService {

    ProductCategoryDto createCategory(ProductCategoryDto productCategoryDto);
    ProductCategoryDto updateCategory(ProductCategoryDto productCategoryDto);
    void deleteCategory(String categoryId);

    ProductCategoryDto getSingleCategory(String categoryId);


}
