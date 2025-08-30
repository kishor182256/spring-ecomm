package com.example.Ecommerce.Ecommerce.repository;

import com.example.Ecommerce.Ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateCategoryServiceRepository extends JpaRepository<ProductCategory, Integer> {


    boolean existsByCategoryName(String categoryName);
}
