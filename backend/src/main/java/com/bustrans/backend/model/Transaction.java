package com.bustrans.backend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private double montant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Transaction() {}

    public Transaction(String type, double montant, Date dateTransaction, Client client) {
        this.type = type;
        this.montant = montant;
        this.dateTransaction = dateTransaction;
        this.client = client;
    }

    // Getters et Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public Date getDateTransaction() { return dateTransaction; }
    public void setDateTransaction(Date dateTransaction) { this.dateTransaction = dateTransaction; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
