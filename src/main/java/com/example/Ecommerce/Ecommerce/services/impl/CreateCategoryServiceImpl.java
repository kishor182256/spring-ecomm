package com.example.Ecommerce.Ecommerce.services.impl;

import com.example.Ecommerce.Ecommerce.dto.ProductCategoryDto;
import com.example.Ecommerce.Ecommerce.entity.ProductCategory;
import com.example.Ecommerce.Ecommerce.exception.ProductCategoryCreateException;
import com.example.Ecommerce.Ecommerce.repository.CreateCategoryServiceRepository;
import com.example.Ecommerce.Ecommerce.services.CreateCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryServiceImpl implements CreateCategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CreateCategoryServiceRepository createCategoryServiceRepository;



    @Override
    public ProductCategoryDto createCategory(ProductCategoryDto productCategoryDto) {

        if (createCategoryServiceRepository.existsByCategoryName(productCategoryDto.getCategoryName())) {
            throw new ProductCategoryCreateException(
                    String.format("A ProductCategory with name '%s' already exists.", productCategoryDto.getCategoryName())
            );
        }


        ProductCategory category = modelMapper.map(productCategoryDto, ProductCategory.class);
        ProductCategory saved = createCategoryServiceRepository.save(category);
        return modelMapper.map(saved, ProductCategoryDto.class);
    }

    @Override
    public ProductCategoryDto updateCategory(ProductCategoryDto productCategoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(String categoryId) {

    }

    @Override
    public ProductCategoryDto getSingleCategory(String categoryId) {
        return null;
    }
}
