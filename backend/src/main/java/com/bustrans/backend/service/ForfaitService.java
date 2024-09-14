package com.bustrans.backend.service;

import com.bustrans.backend.dto.ForfaitDTO;
import com.bustrans.backend.model.Client;
import com.bustrans.backend.model.Forfait;
import com.bustrans.backend.repository.ForfaitRepository;
import com.bustrans.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForfaitService {

    @Autowired
    private ForfaitRepository forfaitRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    // Récupérer l'historique des forfaits d'un client par ID
    public List<ForfaitDTO> getForfaitHistory(Long clientId) {
        List<Forfait> forfaits = forfaitRepository.findByClientId(clientId);
        return forfaits.stream()
                .map(forfait -> new ForfaitDTO(
                        forfait.getTypeForfait(),
                        forfait.getDateActivation(),
                        forfait.getDateExpiration(),
                        forfait.getClient().getId(),
                        forfait.getClient().getRfid()))
                .collect(Collectors.toList());
    }

    // Créer un forfait pour un client et mettre à jour le statut dans la table Client
    public Forfait createForfait(ForfaitDTO forfaitDTO) {
        Client client = clientService.getClientByRFID(forfaitDTO.getRfid());
        if (client == null) {
            throw new IllegalArgumentException("Client introuvable avec le RFID fourni");
        }

        // Calculer les dates d'activation et d'expiration du forfait
        Date dateActivation = new Date();
        Date dateExpiration = calculateExpirationDate(forfaitDTO.getTypeForfait(), dateActivation);

        // Créer le forfait
        Forfait forfait = new Forfait(forfaitDTO.getTypeForfait(), dateActivation, dateExpiration, client);
        forfaitRepository.save(forfait);

        // Mettre à jour le client avec le statut du forfait et la date d'expiration
        client.setForfaitActif(true);  // Forfait activé
        client.setForfaitExpiration(dateExpiration);  // Date d'expiration du forfait
        clientRepository.save(client);  // Enregistrer les modifications dans la table Client

        return forfait;
    }

    // Méthode pour calculer la date d'expiration en fonction du type de forfait
    private Date calculateExpirationDate(String typeForfait, Date dateActivation) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateActivation);
        switch (typeForfait) {
            case "jour":
                cal.add(Calendar.DAY_OF_MONTH, 1);
                break;
            case "semaine":
                cal.add(Calendar.DAY_OF_MONTH, 7);
                break;
            case "mois":
                cal.add(Calendar.MONTH, 1);
                break;
        }
        return cal.getTime();
    }

    // Supprimer un forfait
    public void deleteForfait(Long id) {
        forfaitRepository.deleteById(id);
    }

    // Récupérer le statut du forfait via RFID
    public ForfaitDTO getForfaitStatusByRFID(String rfid) {
        Client client = clientService.getClientByRFID(rfid);
        if (client == null) {
            return null; // Client introuvable
        }

        // Vérifier le statut du forfait (actif ou inactif)
        if (client.isForfaitActif()) {
            Date expirationDate = client.getForfaitExpiration();
            if (expirationDate != null && expirationDate.after(new Date())) {
                return new ForfaitDTO(
                        "Forfait Actif",
                        client.getForfaitExpiration(),
                        client.getForfaitExpiration(),
                        client.getId(),
                        client.getRfid());
            } else {
                return new ForfaitDTO(
                        "Forfait Expiré",
                        client.getForfaitExpiration(),
                        client.getForfaitExpiration(),
                        client.getId(),
                        client.getRfid());
            }
        } else {
            return new ForfaitDTO(
                    "Aucun forfait actif",
                    null,
                    null,
                    client.getId(),
                    client.getRfid());
        }
    }
}
