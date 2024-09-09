package com.bustrans.backend.controller;

import com.bustrans.backend.dto.TransactionDTO;
import com.bustrans.backend.model.Client;
import com.bustrans.backend.model.Transaction;
import com.bustrans.backend.service.ClientService;
import com.bustrans.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    // Méthode pour récupérer toutes les transactions d'un client spécifique
    @GetMapping("/{clientId}")
    public ResponseEntity<List<TransactionDTO>> getClientTransactions(@PathVariable Long clientId) {
        // Récupération du client
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        // Récupération des transactions associées au client
        List<Transaction> transactions = transactionService.getTransactionsByClient(client);

        // Transformation des transactions en DTO
        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getId(),
                        transaction.getType(),
                        transaction.getMontant(),
                        transaction.getDateTransaction(),
                        transaction.getClient().getId(),
                        transaction.getClient().getNom()
                ))
                .collect(Collectors.toList());

        // Retourne la liste des transactions en DTO
        return ResponseEntity.ok(transactionDTOs);
    }

    // Méthode pour récupérer toutes les transactions (pour un usage global si nécessaire)
    @GetMapping("/all")
    public List<TransactionDTO> getAllTransactions() {
        // Récupération de toutes les transactions
        List<Transaction> transactions = transactionService.getAllTransactions();

        // Transformation en DTO
        return transactions.stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getId(),
                        transaction.getType(),
                        transaction.getMontant(),
                        transaction.getDateTransaction(),
                        transaction.getClient().getId(),
                        transaction.getClient().getNom()
                ))
                .collect(Collectors.toList());
    }
}
