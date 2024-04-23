package com.storage.springbootquiz.product.service;

import com.storage.springbootquiz.product.model.Product;
import com.storage.springbootquiz.product.repoistory.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        log.info("Creating product: {}", product);
        product.setCreationDate(new Date());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        log.info("Updating product with ID {}: {}", id, updatedProduct);
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setCategory(updatedProduct.getCategory());
            return productRepository.save(existingProduct);
        } else {
            log.warn("Product with ID {} not found", id);
            return null;
        }
    }
}
