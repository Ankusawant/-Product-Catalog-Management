package com.productcatalog.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.productcatalog.entity.Product;
import com.productcatalog.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> listProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        try {
            Product created = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                        "message", "Data is submitted successfully",
                        "data", created
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to create product: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        try {
            Product updated = productService.updateProduct(id, product);
            return ResponseEntity.ok(Map.of("message", "Product updated successfully", "data", updated));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to update product: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to delete product: " + e.getMessage()));
        }
    }
}
