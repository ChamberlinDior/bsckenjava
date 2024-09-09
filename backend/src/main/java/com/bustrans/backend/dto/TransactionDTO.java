package com.bustrans.backend.dto;

import java.util.Date;

public class TransactionDTO {

    private Long id;
    private String type;
    private double montant;
    private Date dateTransaction;
    private Long clientId;
    private String clientNom;

    // Constructeurs
    public TransactionDTO() {}

    public TransactionDTO(Long id, String type, double montant, Date dateTransaction, Long clientId, String clientNom) {
        this.id = id;
        this.type = type;
        this.montant = montant;
        this.dateTransaction = dateTransaction;
        this.clientId = clientId;
        this.clientNom = clientNom;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }
}
