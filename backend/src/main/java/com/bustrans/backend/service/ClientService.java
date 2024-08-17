package com.bustrans.backend.service;

import com.bustrans.backend.model.Client;
import com.bustrans.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        // Génération automatique du numéro de client s'il n'est pas fourni
        if (client.getNumClient() == null || client.getNumClient().isEmpty()) {
            client.setNumClient(generateClientNumber());
        }

        // Génération automatique du nom de l'agent s'il n'est pas fourni
        if (client.getNomAgent() == null || client.getNomAgent().isEmpty()) {
            client.setNomAgent(generateAgentName());
        }

        // Validation : les champs nom, prénom, quartier, ville ne doivent pas être vides
        if (client.getNom() == null || client.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (client.getPrenom() == null || client.getPrenom().isEmpty()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        if (client.getQuartier() == null || client.getQuartier().isEmpty()) {
            throw new IllegalArgumentException("Le quartier est obligatoire");
        }
        if (client.getVille() == null || client.getVille().isEmpty()) {
            throw new IllegalArgumentException("La ville est obligatoire");
        }

        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client getClientByRFID(String rfid) {
        return clientRepository.findByRfid(rfid).orElse(null);
    }

    public Client updateClient(Long id, Client clientDetails) {
        Client client = getClientById(id);
        if (client != null) {
            client.setNom(clientDetails.getNom());
            client.setPrenom(clientDetails.getPrenom());
            client.setQuartier(clientDetails.getQuartier());
            client.setVille(clientDetails.getVille());
            client.setNumClient(clientDetails.getNumClient());
            if (client.getNomAgent() == null || client.getNomAgent().isEmpty()) {
                client.setNomAgent(generateAgentName());
            }
            return clientRepository.save(client);
        }
        return null;
    }

    public Client assignRFID(Long id, String rfid) {
        Client client = getClientById(id);
        if (client != null) {
            client.setRfid(rfid);
            return clientRepository.save(client);
        }
        return null;
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    private String generateClientNumber() {
        return "CLT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private String generateAgentName() {
        return "Agent-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }
}

