package com.storage.springbootquiz.sales.service;

import com.storage.springbootquiz.sales.model.Transaction;
import com.storage.springbootquiz.sales.repoistory.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Transaction transaction) {
        log.info("Updating transaction: {}", transaction);
        boolean existingTransaction = transactionRepository.existsById(transaction.getId());
        Transaction updatedTransaction = null;
        if (existingTransaction) {
            // Update the transaction
            updatedTransaction = transactionRepository.save(transaction);
        }

        return updatedTransaction;
    }
}
