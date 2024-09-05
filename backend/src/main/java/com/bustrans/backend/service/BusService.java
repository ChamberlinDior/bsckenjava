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

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public Bus getBusById(Long id) {
        return busRepository.findById(id).orElse(null);
    }

    public Bus updateBus(Long id, Bus busDetails) {
        Bus bus = getBusById(id);
        if (bus != null) {
            bus.setModele(busDetails.getModele());
            bus.setMatricule(busDetails.getMatricule());
            bus.setMarque(busDetails.getMarque());
            bus.setMacAddress(busDetails.getMacAddress());
            bus.setChauffeurNom(busDetails.getChauffeurNom());
            bus.setChauffeurUniqueNumber(busDetails.getChauffeurUniqueNumber());
            bus.setLastDestination(busDetails.getLastDestination());
            bus.setDebutTrajet(busDetails.getDebutTrajet());
            bus.setFinTrajet(busDetails.getFinTrajet());
            return busRepository.save(bus);
        }
        return null;
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    public Bus getBusByMacAddress(String macAddress) {
        return busRepository.findByMacAddress(macAddress);
    }

    // Méthode pour mettre à jour les informations du chauffeur via l'adresse MAC
    public Bus updateChauffeurByMacAddress(String macAddress, String chauffeurNom, String chauffeurUniqueNumber) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            bus.setChauffeurNom(chauffeurNom);
            bus.setChauffeurUniqueNumber(chauffeurUniqueNumber);
            return busRepository.save(bus);
        }
        return null;
    }
}
