package com.example.Ecommerce.Ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {

    private int categoryId;
    private String categoryName;
    private String description;


}
