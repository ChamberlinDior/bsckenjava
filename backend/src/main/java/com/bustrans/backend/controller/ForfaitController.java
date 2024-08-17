package com.bustrans.backend.controller;

import com.bustrans.backend.dto.ForfaitDTO;
import com.bustrans.backend.model.Client;
import com.bustrans.backend.model.Forfait;
import com.bustrans.backend.service.ClientService;
import com.bustrans.backend.service.ForfaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/forfaits")
public class ForfaitController {

    @Autowired
    private ForfaitService forfaitService;

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Forfait> createForfait(@RequestBody ForfaitDTO forfaitDTO) {
        Client client = clientService.getClientByRFID(forfaitDTO.getRfid());

        if (client == null) {
            return ResponseEntity.badRequest().build();
        }

        Date dateActivation = new Date();
        Date dateExpiration = calculateExpirationDate(forfaitDTO.getTypeForfait(), dateActivation);

        // Création du forfait
        Forfait forfait = new Forfait(forfaitDTO.getTypeForfait(), dateActivation, dateExpiration, client);
        Forfait savedForfait = forfaitService.saveForfait(forfait);

        // Mise à jour des informations du client
        updateClientWithForfaitInfo(client, forfaitDTO.getTypeForfait(), dateExpiration);

        return ResponseEntity.ok(savedForfait);
    }

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

    private void updateClientWithForfaitInfo(Client client, String typeForfait, Date dateExpiration) {
        // Mise à jour du solde en fonction du type de forfait
        switch (typeForfait) {
            case "jour":
                client.setBalance(client.getBalance() + 100); // Ajoute 100 XAF pour un forfait jour
                break;
            case "semaine":
                client.setBalance(client.getBalance() + 500); // Ajoute 500 XAF pour un forfait semaine
                break;
            case "mois":
                client.setBalance(client.getBalance() + 2500); // Ajoute 2500 XAF pour un forfait mois
                break;
        }

        // Mise à jour de la date d'expiration du forfait et activation du forfait
        client.setForfaitExpiration(dateExpiration);
        client.setForfaitActif(true);

        // Sauvegarde des modifications du client
        clientService.saveClient(client);
    }

    // New endpoint to fetch active forfaits by RFID
    @GetMapping("/active/{rfid}")
    public ResponseEntity<List<Forfait>> getActiveForfaitsByClientRFID(@PathVariable String rfid) {
        Client client = clientService.getClientByRFID(rfid);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        List<Forfait> activeForfaits = forfaitService.getAllForfaits().stream()
                .filter(f -> f.getClient().equals(client) && f.getDateExpiration().after(new Date()))
                .collect(Collectors.toList());

        if (activeForfaits.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(activeForfaits);
    }
}
