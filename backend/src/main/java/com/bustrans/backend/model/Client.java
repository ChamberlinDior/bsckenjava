package com.bustrans.backend.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numClient;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String quartier;
    private String ville;

    @Column(nullable = false)
    private String nomAgent;

    @Column(nullable = true, unique = true)
    private String rfid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreation;

    @Column(nullable = false)
    private int balance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date forfaitExpiration;

    @Column(nullable = false)
    private boolean forfaitActif;

    public Client() {
        this.numClient = "CLT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.nomAgent = "Agent-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        this.balance = 0;
        this.forfaitActif = false;
    }

    @PrePersist
    protected void onCreate() {
        this.dateCreation = new Date();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getForfaitExpiration() {
        return forfaitExpiration;
    }

    public void setForfaitExpiration(Date forfaitExpiration) {
        this.forfaitExpiration = forfaitExpiration;
    }

    public boolean isForfaitActif() {
        return forfaitActif;
    }

    public void setForfaitActif(boolean forfaitActif) {
        this.forfaitActif = forfaitActif;
    }
}
