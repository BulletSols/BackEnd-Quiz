package com.storage.springbootquiz.product.service;

import com.storage.springbootquiz.product.model.ProductDetails;
import com.storage.springbootquiz.product.repoistory.ProductDetailsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;

    public ProductDetails addProductDetails(ProductDetails ProductDetails) {
        ProductDetails productDetails = productDetailsRepository.save(ProductDetails);
        return productDetails;
    }

    public ProductDetails updateProductDetails(ProductDetails productDetails) {
        log.info("Updating productDetails : {}", productDetails);
        boolean existingProductDetails = productDetailsRepository.existsById(productDetails.getId());
        ProductDetails updatedProductDetails = null;
        if (existingProductDetails) {
            // Update the productDetails
            updatedProductDetails = productDetailsRepository.save(productDetails);
        }

        return updatedProductDetails;
    }
}
