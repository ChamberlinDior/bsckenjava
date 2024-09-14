package com.bustrans.backend.service;

import com.bustrans.backend.model.Bus;
import com.bustrans.backend.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    // Récupérer tous les bus
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Enregistrer un bus
    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    // Récupérer un bus par son adresse MAC
    public Bus getBusByMacAddress(String macAddress) {
        return busRepository.findByMacAddress(macAddress);
    }

    // Mettre à jour le chauffeur et la destination
    public Bus updateChauffeurAndDestinationByMacAddress(String macAddress, String lastDestination, String chauffeurNom, String chauffeurUniqueNumber) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            bus.setLastDestination(lastDestination);
            bus.setChauffeurNom(chauffeurNom);  // Mise à jour du nom du chauffeur
            bus.setChauffeurUniqueNumber(chauffeurUniqueNumber);  // Mise à jour du numéro unique du chauffeur
            return busRepository.save(bus);
        }
        return null;
    }

    // Démarrer un trajet
    public Bus startTrip(String macAddress, String lastDestination) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            bus.setDebutTrajet(new java.util.Date());
            bus.setLastDestination(lastDestination);
            return busRepository.save(bus);
        }
        return null;
    }

    // Terminer un trajet
    public Bus endTrip(String macAddress) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            bus.setFinTrajet(new java.util.Date());
            return busRepository.save(bus);
        }
        return null;
    }

    // Mettre à jour uniquement la destination du bus
    public Bus updateLastDestination(String macAddress, String lastDestination) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            bus.setLastDestination(lastDestination);
            return busRepository.save(bus);
        }
        return null;
    }

    // Créer ou mettre à jour la destination
    public Bus createOrUpdateLastDestination(String macAddress, String lastDestination) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            if (bus.getLastDestination() == null || !bus.getLastDestination().equals(lastDestination)) {
                bus.setLastDestination(lastDestination);
                return busRepository.save(bus);
            }
            return bus;
        }
        return null;
    }
}
