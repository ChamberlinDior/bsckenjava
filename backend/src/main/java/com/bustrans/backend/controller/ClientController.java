package com.bustrans.backend.controller;

import com.bustrans.backend.dto.ClientDTO;
import com.bustrans.backend.model.Client;
import com.bustrans.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Obtenir tous les clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Créer un nouveau client
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    // Obtenir un client par son ID et retourner un DTO
    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setNumClient(client.getNumClient());
        clientDTO.setNom(client.getNom());
        clientDTO.setPrenom(client.getPrenom());
        clientDTO.setQuartier(client.getQuartier());
        clientDTO.setVille(client.getVille());
        clientDTO.setDateCreation(client.getDateCreation().toString());
        clientDTO.setNomAgent(client.getNomAgent());

        return clientDTO;
    }

    // Obtenir un client par RFID
    @GetMapping("/rfid/{rfid}")
    public Client getClientByRFID(@PathVariable String rfid) {
        return clientService.getClientByRFID(rfid);
    }

    // Mettre à jour un client existant
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientService.updateClient(id, clientDetails);
    }

    // Assigner un RFID à un client existant
    @PutMapping("/{id}/rfid")
    public Client assignRFID(@PathVariable Long id, @RequestBody String rfid) {
        return clientService.assignRFID(id, rfid);
    }

    // Supprimer un client
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
