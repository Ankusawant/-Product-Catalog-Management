package com.productcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productcatalog.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}