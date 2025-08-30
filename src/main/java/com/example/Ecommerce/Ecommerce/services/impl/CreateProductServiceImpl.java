package com.example.Ecommerce.Ecommerce.services.impl;

import com.example.Ecommerce.Ecommerce.dto.ProductDto;
import com.example.Ecommerce.Ecommerce.entity.Product;
import com.example.Ecommerce.Ecommerce.exception.ProductCreateException;
import com.example.Ecommerce.Ecommerce.repository.CreateProductRepository;
import com.example.Ecommerce.Ecommerce.services.CreateProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductServiceImpl implements CreateProductService {

    @Autowired
    private CreateProductRepository createProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        try {
            if (createProductRepository.existsByProductCategoryCategoryName(productDto.getProductName())) {
                throw new ProductCreateException(
                        String.format("A Product with name '%s' already exists.", productDto.getProductName())
                );
            }
            Product product = modelMapper.map(productDto, Product.class);
            Product savedProduct = createProductRepository.save(product);
            return modelMapper.map(savedProduct, ProductDto.class);
        } catch (ProductCreateException e) {
            System.err.println("Product creation failed: " + e.getMessage());
            System.err.println("ProductDto: " + productDto.toString());
            throw new RuntimeException("Product creation failed due to name conflict", e);
        }
    }



    @Override
    public ProductDto updateProduct(ProductDto productCategoryDto) {
        return null;
    }

    @Override
    public void deleteProduct(String categoryId) {

    }

    @Override
    public ProductDto getSingleProduct(String categoryId) {
        return null;
    }


}
