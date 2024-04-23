package com.storage.springbootquiz.sales.service;

import com.storage.springbootquiz.product.model.ProductDetails;
import com.storage.springbootquiz.product.service.ProductDetailsService;
import com.storage.springbootquiz.product.service.ProductService;
import com.storage.springbootquiz.sales.model.Sales;
import com.storage.springbootquiz.sales.repoistory.SalesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SalesService {

    private final ProductService productService;
    private final SalesRepository salesRepository;
    private final ProductDetailsService productDetailsService;

    public List<Sales> getAllSales() {
        log.info("Fetching all sales operations.");
        List<Sales> sales = salesRepository.findAll();
        sales.forEach(sale -> sale.getProductDetails().forEach(productDetails -> productDetails.setProduct(productService.getProduct(productDetails.getProductId()))));
        return sales;
    }

    @Transactional
    public Sales createSales(Sales sales) {
        log.info("Creating sales: {}", sales);

        for (ProductDetails productDetails : sales.getProductDetails()) {
            productDetailsService.addProductDetails(productDetails);
            log.info("ProductDetails updated: {}", productDetails);
        }

        return salesRepository.save(sales);
    }

    @Transactional
    public Sales updateSales(Sales sales) {
        log.info("Updating sales: {}", sales);
        boolean existingSales = salesRepository.existsById(sales.getId());
        Sales updatedSales = null;
        if (existingSales) {
            // Update the Sales
            updatedSales = salesRepository.save(sales);

            // Update the productDetails associated with the Sales
            for (ProductDetails productDetails : sales.getProductDetails()) {
                productDetailsService.updateProductDetails(productDetails);
                log.info("ProductDetails updated: {}", productDetails);
            }
        }

        return updatedSales;
    }
}
