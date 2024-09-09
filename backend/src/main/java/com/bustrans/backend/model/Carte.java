package com.bustrans.backend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String nom;

    @Column(nullable = true)
    private String prenom;

    @Column(nullable = true)
    private String nomAgent;

    @Column(nullable = true)
    private String rfid;

    @Column(nullable = true)
    private Date dateCreation;

    @Column(nullable = true)
    private Date dateDelivrance;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
