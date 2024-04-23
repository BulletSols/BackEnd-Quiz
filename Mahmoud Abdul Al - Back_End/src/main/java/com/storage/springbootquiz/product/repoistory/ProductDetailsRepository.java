package com.storage.springbootquiz.product.repoistory;

import com.storage.springbootquiz.product.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    List<ProductDetails> findBySalesId(Long salesId);
}
