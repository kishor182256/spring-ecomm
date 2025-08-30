package com.example.Ecommerce.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    private String productName;

    private  String description;

    private int price;
    private int quantity;
    private Date createdAt;
    private Date updatedAt;

    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ProductCategory productCategory;



}
