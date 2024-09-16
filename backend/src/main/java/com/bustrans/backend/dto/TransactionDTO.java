package com.bustrans.backend.dto;

import java.util.Date;

public class TransactionDTO {

    private Long id;
    private String terminalId;
    private String forfaitType;
    private String clientRfid;
    private String utilisateurId;
    private Date dateTransaction;

    // Constructeur vide
    public TransactionDTO() {}

    // Constructeur avec tous les paramètres
    public TransactionDTO(Long id, String terminalId, String forfaitType, String clientRfid, String utilisateurId, Date dateTransaction) {
        this.id = id;
        this.terminalId = terminalId;
        this.forfaitType = forfaitType;
        this.clientRfid = clientRfid;
        this.utilisateurId = utilisateurId;
        this.dateTransaction = dateTransaction;
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
