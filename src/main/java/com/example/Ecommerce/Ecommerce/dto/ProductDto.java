package com.example.Ecommerce.Ecommerce.dto;

import com.example.Ecommerce.Ecommerce.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productName;
    private  String description;
    private int price;
    private int quantity;
    private Date createdAt;
    private Date updatedAt;
    private ProductCategory productCategory;

}
