package com.bustrans.backend.controller;

import com.bustrans.backend.dto.BusDTO;
import com.bustrans.backend.model.Bus;
import com.bustrans.backend.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }

    @PostMapping
    public Bus createBus(@RequestBody BusDTO busDTO) {
        Bus bus = convertToEntity(busDTO);
        return busService.saveBus(bus);
    }

    @GetMapping("/{id}")
    public Bus getBusById(@PathVariable Long id) {
        return busService.getBusById(id);
    }

    @PutMapping("/{id}")
    public Bus updateBus(@PathVariable Long id, @RequestBody BusDTO busDTO) {
        Bus bus = convertToEntity(busDTO);
        return busService.updateBus(id, bus);
    }

    @DeleteMapping("/{id}")
    public void deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
    }

    // Recherche par adresse MAC du TPE
    @GetMapping("/mac/{macAddress}")
    public ResponseEntity<Bus> getBusByMacAddress(@PathVariable String macAddress) {
        Bus bus = busService.getBusByMacAddress(macAddress);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }

    // Mise à jour de la destination, début et fin de trajet via MAC du TPE
    @PostMapping("/mac/{macAddress}/update-trajet")
    public ResponseEntity<?> updateTrajetByMac(@PathVariable String macAddress, @RequestBody BusDTO busDTO) {
        Bus bus = busService.getBusByMacAddress(macAddress);
        if (bus == null) {
            return ResponseEntity.notFound().build();
        }
        bus.setLastDestination(busDTO.getLastDestination());
        bus.setDebutTrajet(busDTO.getDebutTrajet());
        bus.setFinTrajet(busDTO.getFinTrajet());
        busService.saveBus(bus);
        return ResponseEntity.ok("Trajet mis à jour avec succès.");
    }

    // Mise à jour des informations du chauffeur via MAC du TPE
    @PostMapping("/mac/{macAddress}/update-chauffeur")
    public ResponseEntity<?> updateChauffeurByMac(@PathVariable String macAddress, @RequestBody BusDTO busDTO) {
        Bus bus = busService.getBusByMacAddress(macAddress);
        if (bus == null) {
            return ResponseEntity.notFound().build();
        }
        bus.setChauffeurNom(busDTO.getChauffeurNom());
        bus.setChauffeurUniqueNumber(busDTO.getChauffeurUniqueNumber());
        busService.saveBus(bus);
        return ResponseEntity.ok("Chauffeur mis à jour avec succès.");
    }

    private Bus convertToEntity(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setId(busDTO.getId());
        bus.setModele(busDTO.getModele());
        bus.setMatricule(busDTO.getMatricule());
        bus.setMarque(busDTO.getMarque());
        bus.setMacAddress(busDTO.getMacAddress());
        bus.setChauffeurNom(busDTO.getChauffeurNom());
        bus.setChauffeurUniqueNumber(busDTO.getChauffeurUniqueNumber());
        bus.setLastDestination(busDTO.getLastDestination());
        bus.setDebutTrajet(busDTO.getDebutTrajet());
        bus.setFinTrajet(busDTO.getFinTrajet());
        return bus;
    }
}
