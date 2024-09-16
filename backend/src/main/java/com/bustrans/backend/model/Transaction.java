package com.bustrans.backend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String terminalId;

    @Column(nullable = false)
    private String forfaitType;

    @Column(nullable = false)
    private String clientRfid;

    @Column(nullable = false)
    private String utilisateurId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateTransaction;

    public Transaction() {
        this.dateTransaction = new Date();
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getForfaitType() {
        return forfaitType;
    }

    public void setForfaitType(String forfaitType) {
        this.forfaitType = forfaitType;
    }

    public String getClientRfid() {
        return clientRfid;
    }

    public void setClientRfid(String clientRfid) {
        this.clientRfid = clientRfid;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
}
