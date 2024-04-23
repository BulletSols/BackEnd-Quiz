package com.storage.springbootquiz.sales;

import com.storage.springbootquiz.sales.model.Sales;
import com.storage.springbootquiz.sales.service.SalesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
@Slf4j
public class SaleController {

    private final SalesService saleService;


    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales() {
        log.info("Fetching all sales.");
        List<Sales> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @PostMapping
    public ResponseEntity<Sales> createSale(@RequestBody Sales sale) {
        log.info("Creating sales with transactions: {}", sale);
        Sales createdSale = saleService.createSales(sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
    }

    @PutMapping
    public ResponseEntity<Sales> updateSale(@RequestBody Sales sale) {
        log.info("Updating sale: {}", sale);
        Sales updatedSale = saleService.updateSales(sale);
        if(updatedSale != null)
            return ResponseEntity.ok(updatedSale);
        return ResponseEntity.notFound().build();
    }
}
