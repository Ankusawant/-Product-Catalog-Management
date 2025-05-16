package com.productcatalog.service;

import com.productcatalog.entity.Product;
import com.productcatalog.exception.ResourceNotFoundException;
import com.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    @Override
    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            logger.severe("Failed to retrieve products: " + e.getMessage());
            throw new RuntimeException("Unable to fetch product list", e);
        }
    }

    @Override
    public Product getProductById(Long id) {
        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        } catch (ResourceNotFoundException e) {
            logger.warning("Product not found: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.severe("Error retrieving product with ID " + id + ": " + e.getMessage());
            throw new RuntimeException("Unable to fetch product by ID", e);
        }
    }

    @Override
    public Product createProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            logger.severe("Failed to create product: " + e.getMessage());
            throw new RuntimeException("Unable to create product", e);
        }
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        try {
            Product existing = getProductById(id);
            existing.setName(updatedProduct.getName());
            existing.setDescription(updatedProduct.getDescription());
            existing.setCategory(updatedProduct.getCategory());
            existing.setPrice(updatedProduct.getPrice());
            existing.setInStock(updatedProduct.isInStock());
            return productRepository.save(existing);
        } catch (ResourceNotFoundException e) {
            logger.warning("Update failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.severe("Error updating product with ID " + id + ": " + e.getMessage());
            throw new RuntimeException("Unable to update product", e);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            Product product = getProductById(id);
            productRepository.delete(product);
        } catch (ResourceNotFoundException e) {
            logger.warning("Delete failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.severe("Error deleting product with ID " + id + ": " + e.getMessage());
            throw new RuntimeException("Unable to delete product", e);
        }
    }
}
