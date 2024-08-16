package com.bustrans.backend.repository;

import com.bustrans.backend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Custom query methods can be added here if needed
}