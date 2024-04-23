package com.storage.springbootquiz.sales.repoistory;

import com.storage.springbootquiz.sales.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
}
