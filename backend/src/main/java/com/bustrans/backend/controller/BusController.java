package com.bustrans.backend.controller;

import com.bustrans.backend.dto.BusDTO;
import com.bustrans.backend.model.Bus;
import com.bustrans.backend.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/plates")
    public ResponseEntity<List<String>> getAllBusPlates() {
        List<String> plates = busService.getAllBuses().stream()
                .map(Bus::getMatricule)
                .collect(Collectors.toList());
        return ResponseEntity.ok(plates);
    }

    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Bus> getBusByMatricule(@PathVariable String matricule) {
        Bus bus = busService.getBusByPlateNumber(matricule);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/select-destination")
    public ResponseEntity<Bus> selectDestination(@PathVariable Long id, @RequestParam String destination) {
        Bus bus = busService.getBusById(id);
        if (bus != null) {
            bus.setLastDestination(destination);
            busService.saveBus(bus);
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Bus> startTrajet(@PathVariable Long id) {
        Bus bus = busService.startTrajet(id);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<Bus> endTrajet(@PathVariable Long id) {
        Bus bus = busService.endTrajet(id);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }

    // Mise à jour des informations du chauffeur via le numéro de plaque (matricule)
    @PostMapping("/matricule/{matricule}/chauffeur")
    public ResponseEntity<Bus> updateChauffeurInfoByMatricule(
            @PathVariable String matricule,
            @RequestBody Map<String, String> chauffeurInfo) {
        Bus bus = busService.getBusByPlateNumber(matricule);
        if (bus != null) {
            bus.setChauffeurNom(chauffeurInfo.get("chauffeurName"));
            bus.setChauffeurUniqueNumber(chauffeurInfo.get("chauffeurNumber"));
            busService.saveBus(bus);
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }

    private Bus convertToEntity(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setId(busDTO.getId());
        bus.setModele(busDTO.getModele());
        bus.setMatricule(busDTO.getMatricule());
        bus.setMarque(busDTO.getMarque());
        bus.setChauffeurNom(busDTO.getChauffeurNom());
        bus.setChauffeurUniqueNumber(busDTO.getChauffeurUniqueNumber());
        bus.setDebutTrajet(busDTO.getDebutTrajet());
        bus.setFinTrajet(busDTO.getFinTrajet());
        bus.setLastDestination(busDTO.getLastDestination());
        return bus;
    }
}
