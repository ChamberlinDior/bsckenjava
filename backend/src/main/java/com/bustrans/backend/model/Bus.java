package com.bustrans.backend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modele;

    @Column(nullable = false, unique = true)
    private String matricule;

    @Column(nullable = false)
    private String marque;

    @Column(nullable = false, unique = true)
    private String macAddress;

    @Column(nullable = true, unique = true)
    private String ipAddress;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "debut_trajet")
    private Date debutTrajet;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fin_trajet")
    private Date finTrajet;

    @Column(name = "chauffeur_nom")
    private String chauffeurNom;

    @Column(name = "chauffeur_unique_number")
    private String chauffeurUniqueNumber;

    @Column(name = "last_destination")
    private String lastDestination;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getDebutTrajet() {
        return debutTrajet;
    }

    public void setDebutTrajet(Date debutTrajet) {
        this.debutTrajet = debutTrajet;
    }

    public Date getFinTrajet() {
        return finTrajet;
    }

    public void setFinTrajet(Date finTrajet) {
        this.finTrajet = finTrajet;
    }

    public String getChauffeurNom() {
        return chauffeurNom;
    }

    public void setChauffeurNom(String chauffeurNom) {
        this.chauffeurNom = chauffeurNom;
    }

    public String getChauffeurUniqueNumber() {
        return chauffeurUniqueNumber;
    }

    public void setChauffeurUniqueNumber(String chauffeurUniqueNumber) {
        this.chauffeurUniqueNumber = chauffeurUniqueNumber;
    }

    public String getLastDestination() {
        return lastDestination;
    }

    public void setLastDestination(String lastDestination) {
        this.lastDestination = lastDestination;
    }
}
