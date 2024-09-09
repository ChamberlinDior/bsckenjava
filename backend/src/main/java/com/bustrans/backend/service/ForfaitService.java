package com.bustrans.backend.service;

import com.bustrans.backend.dto.ForfaitDTO;
import com.bustrans.backend.model.Client;
import com.bustrans.backend.model.Forfait;
import com.bustrans.backend.repository.ForfaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForfaitService {

    @Autowired
    private ForfaitRepository forfaitRepository;

    @Autowired
    private ClientService clientService;

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

    // Créer un forfait pour un client
    public Forfait createForfait(ForfaitDTO forfaitDTO) {
        Client client = clientService.getClientByRFID(forfaitDTO.getRfid());
        if (client == null) {
            throw new IllegalArgumentException("Client introuvable avec le RFID fourni");
        }

        Date dateActivation = new Date();
        Date dateExpiration = calculateExpirationDate(forfaitDTO.getTypeForfait(), dateActivation);

        Forfait forfait = new Forfait(forfaitDTO.getTypeForfait(), dateActivation, dateExpiration, client);
        return forfaitRepository.save(forfait);
    }

    // Méthode pour calculer la date d'expiration
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
    public String getForfaitStatusByRFID(String rfid) {
        Client client = clientService.getClientByRFID(rfid);
        if (client == null) {
            return "Client introuvable";
        }

        Date expirationDate = client.getForfaitExpiration();
        if (expirationDate != null && expirationDate.after(new Date())) {
            return "Forfait actif jusqu'au " + expirationDate;
        } else {
            return "Aucun forfait actif";
        }
    }
}
