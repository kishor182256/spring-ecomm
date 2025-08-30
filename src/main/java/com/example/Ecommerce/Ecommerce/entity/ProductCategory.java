package com.example.Ecommerce.Ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "product_category",
        uniqueConstraints = @UniqueConstraint(columnNames = {"categoryName"})
)
public class ProductCategory {

    @Id
    @Column(name="categoryId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;
    private String categoryName;
    private String description;


}
