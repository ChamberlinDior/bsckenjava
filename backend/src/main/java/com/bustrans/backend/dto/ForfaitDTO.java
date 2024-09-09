package com.bustrans.backend.dto;

import java.util.Date;

public class ForfaitDTO {

    private String typeForfait;
    private Date dateActivation;
    private Date dateExpiration;
    private Long clientId;
    private String rfid;

    // Constructeur vide
    public ForfaitDTO() {}

    // Constructeur avec tous les paramètres
    public ForfaitDTO(String typeForfait, Date dateActivation, Date dateExpiration, Long clientId, String rfid) {
        this.typeForfait = typeForfait;
        this.dateActivation = dateActivation;
        this.dateExpiration = dateExpiration;
        this.clientId = clientId;
        this.rfid = rfid;
    }

    // Getters et Setters
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
}
