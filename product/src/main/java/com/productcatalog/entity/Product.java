package com.productcatalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    private String category;

    @Positive(message = "Price must be a positive value")
    private double price;

    private boolean inStock;

    // Default constructor
    public Product() {
        super();
    }

    // Parameterized constructor
    public Product(Long id, String name, String description, String category, double price, boolean inStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.inStock = inStock;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    // toString()
    @Override
    public String toString() {
        return "Product [id=" + id +
               ", name=" + name +
               ", description=" + description +
               ", category=" + category +
               ", price=" + price +
               ", inStock=" + inStock + "]";
    }
}
