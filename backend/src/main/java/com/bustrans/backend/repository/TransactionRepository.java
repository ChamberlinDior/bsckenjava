package com.bustrans.backend.repository;

import com.bustrans.backend.model.Transaction;
import com.bustrans.backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Méthode pour récupérer les transactions par client
    List<Transaction> findByClient(Client client);
}
