package com.bustrans.backend.service;

import com.bustrans.backend.model.Bus;
import com.bustrans.backend.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            bus.setChauffeurNom(busDetails.getChauffeurNom());
            bus.setChauffeurUniqueNumber(busDetails.getChauffeurUniqueNumber());
            bus.setLastDestination(busDetails.getLastDestination());
            return busRepository.save(bus);
        }
        return null;
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    public Bus startTrajet(Long id) {
        Bus bus = getBusById(id);
        if (bus != null) {
            bus.setDebutTrajet(new Date());
            return busRepository.save(bus);
        }
        return null;
    }

    public Bus endTrajet(Long id) {
        Bus bus = getBusById(id);
        if (bus != null) {
            bus.setFinTrajet(new Date());
            return busRepository.save(bus);
        }
        return null;
    }

    public Bus getBusByPlateNumber(String plateNumber) {
        return busRepository.findByMatricule(plateNumber);
    }
}
