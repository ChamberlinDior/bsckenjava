package com.bustrans.backend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Forfait {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String typeForfait;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateActivation;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateExpiration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Forfait() {}

    public Forfait(String typeForfait, Date dateActivation, Date dateExpiration, Client client) {
        this.typeForfait = typeForfait;
        this.dateActivation = dateActivation;
        this.dateExpiration = dateExpiration;
        this.client = client;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeForfait() {
        return typeForfait;
    }

    public void setTypeForfait(String typeForfait) {
        this.typeForfait = typeForfait;
    }

    public Date getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(Date dateActivation) {
        this.dateActivation = dateActivation;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
