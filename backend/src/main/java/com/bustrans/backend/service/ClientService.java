package com.bustrans.backend.service;

import com.bustrans.backend.model.Client;
import com.bustrans.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        // Génération automatique du numéro de client et de l'agent, ainsi que la date de création
        if (client.getNumClient() == null || client.getNumClient().isEmpty()) {
            client.setNumClient(generateClientNumber());
        }

        if (client.getNomAgent() == null || client.getNomAgent().isEmpty()) {
            client.setNomAgent(generateAgentName());
        }

        if (client.getDateCreation() == null) {
            client.setDateCreation(new Date());
        }

        // Validation des champs obligatoires
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

            // Mise à jour du nom de l'agent et de la date de création si fournis
            if (clientDetails.getNomAgent() != null && !clientDetails.getNomAgent().isEmpty()) {
                client.setNomAgent(clientDetails.getNomAgent());
            }

            if (clientDetails.getDateCreation() != null) {
                client.setDateCreation(clientDetails.getDateCreation());
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
