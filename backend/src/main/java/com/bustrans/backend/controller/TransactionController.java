package com.bustrans.backend.controller;

import com.bustrans.backend.dto.TransactionDTO;
import com.bustrans.backend.model.Transaction;
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

    // Récupérer toutes les transactions
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<TransactionDTO> transactionDTOs = transactions.stream().map(transaction -> new TransactionDTO(
                transaction.getId(),
                transaction.getTerminalId(),
                transaction.getForfaitType(),
                transaction.getClientRfid(),
                transaction.getUtilisateurId(),
                transaction.getDateTransaction()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(transactionDTOs);
    }

    // Créer une nouvelle transaction
    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setTerminalId(transactionDTO.getTerminalId());
        transaction.setForfaitType(transactionDTO.getForfaitType());
        transaction.setClientRfid(transactionDTO.getClientRfid());
        transaction.setUtilisateurId(transactionDTO.getUtilisateurId());
        transaction.setDateTransaction(transactionDTO.getDateTransaction());

        Transaction newTransaction = transactionService.createTransaction(transaction);
        TransactionDTO newTransactionDTO = new TransactionDTO(
                newTransaction.getId(),
                newTransaction.getTerminalId(),
                newTransaction.getForfaitType(),
                newTransaction.getClientRfid(),
                newTransaction.getUtilisateurId(),
                newTransaction.getDateTransaction()
        );

        return ResponseEntity.ok(newTransactionDTO);
    }
}
