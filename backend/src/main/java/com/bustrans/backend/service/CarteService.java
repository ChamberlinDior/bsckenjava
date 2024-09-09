package com.bustrans.backend.service;

import com.bustrans.backend.dto.CarteDTO;
import com.bustrans.backend.model.Carte;
import com.bustrans.backend.model.Client;
import com.bustrans.backend.repository.CarteRepository;
import com.bustrans.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteService {

    @Autowired
    private CarteRepository carteRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<CarteDTO> getCartesByClientId(Long clientId) {
        List<Carte> cartes = carteRepository.findByClientId(clientId);
        return cartes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CarteDTO createCarte(CarteDTO carteDTO) {
        // Vérifier si le RFID est fourni
        if (carteDTO.getRfid() == null || carteDTO.getRfid().isEmpty()) {
            throw new RuntimeException("Le numéro RFID est obligatoire");
        }

        Carte carte = convertToEntity(carteDTO);

        // Assigner des dates si elles ne sont pas fournies
        if (carte.getDateCreation() == null) {
            carte.setDateCreation(new Date());
        }

        if (carte.getDateDelivrance() == null) {
            carte.setDateDelivrance(new Date());
        }

        // Enregistrer la carte et retourner le DTO correspondant
        Carte savedCarte = carteRepository.save(carte);
        return convertToDto(savedCarte);
    }

    private CarteDTO convertToDto(Carte carte) {
        CarteDTO carteDTO = new CarteDTO();
        carteDTO.setId(carte.getId());
        carteDTO.setClientId(carte.getClient().getId());
        carteDTO.setNom(carte.getNom());
        carteDTO.setPrenom(carte.getPrenom());
        carteDTO.setNomAgent(carte.getNomAgent());
        carteDTO.setDateCreation(carte.getDateCreation());
        carteDTO.setDateDelivrance(carte.getDateDelivrance());
        carteDTO.setRfid(carte.getRfid());
        return carteDTO;
    }

    private Carte convertToEntity(CarteDTO carteDTO) {
        Carte carte = new Carte();
        Client client = clientRepository.findById(carteDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        carte.setClient(client);
        carte.setNom(carteDTO.getNom());
        carte.setPrenom(carteDTO.getPrenom());
        carte.setNomAgent(carteDTO.getNomAgent());
        carte.setRfid(carteDTO.getRfid()); // Peut être null
        carte.setDateDelivrance(carteDTO.getDateDelivrance());
        carte.setDateCreation(carteDTO.getDateCreation());
        return carte;
    }
}
