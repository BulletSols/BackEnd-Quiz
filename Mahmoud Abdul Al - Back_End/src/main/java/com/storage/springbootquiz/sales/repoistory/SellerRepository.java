package com.storage.springbootquiz.sales.repoistory;

import com.storage.springbootquiz.sales.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {


}
