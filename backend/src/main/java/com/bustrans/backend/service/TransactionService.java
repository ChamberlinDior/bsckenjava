package com.bustrans.backend.service;

import com.bustrans.backend.model.Transaction;
import com.bustrans.backend.model.Client;
import com.bustrans.backend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Récupère les transactions d'un client spécifique
    public List<Transaction> getTransactionsByClient(Client client) {
        return transactionRepository.findByClient(client);
    }

    // Récupère toutes les transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Sauvegarde une nouvelle transaction
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
