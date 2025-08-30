package com.example.Ecommerce.Ecommerce.repository;

import com.example.Ecommerce.Ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateProductRepository extends JpaRepository<Product,String> {

    boolean existsByProductCategoryCategoryName(String productName);

}
