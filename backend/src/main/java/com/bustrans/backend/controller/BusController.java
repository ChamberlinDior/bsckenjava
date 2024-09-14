package com.bustrans.backend.controller;

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

    // Récupérer tous les bus
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    // Récupérer un bus par son adresse MAC
    @GetMapping("/mac/{macAddress}")
    public ResponseEntity<Bus> getBusByMacAddress(@PathVariable String macAddress) {
        Bus bus = busService.getBusByMacAddress(macAddress);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }

    // Créer un nouveau bus
    @PostMapping("/create")
    public ResponseEntity<?> createBus(@RequestBody Bus bus) {
        try {
            Bus newBus = busService.saveBus(bus);
            return ResponseEntity.ok(newBus);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création du bus : " + e.getMessage());
        }
    }

    // Mettre à jour le chauffeur et la destination par adresse MAC
    @PostMapping("/mac/{macAddress}/update-chauffeur-destination")
    public ResponseEntity<?> updateChauffeurAndDestinationByMac(
            @PathVariable String macAddress,
            @RequestParam String lastDestination,
            @RequestParam String chauffeurNom,
            @RequestParam String chauffeurUniqueNumber) {  // Ajout de chauffeurNom et chauffeurUniqueNumber
        Bus bus = busService.updateChauffeurAndDestinationByMacAddress(macAddress, lastDestination, chauffeurNom, chauffeurUniqueNumber);
        if (bus != null) {
            return ResponseEntity.ok("Chauffeur et destination mis à jour avec succès.");
        }
        return ResponseEntity.notFound().build();
    }

    // Démarrer un trajet par adresse MAC
    @PostMapping("/mac/{macAddress}/start-trip")
    public ResponseEntity<?> startTrip(@PathVariable String macAddress,
                                       @RequestParam String lastDestination) {
        Bus bus = busService.startTrip(macAddress, lastDestination);
        if (bus != null) {
            return ResponseEntity.ok("Trajet démarré avec succès.");
        }
        return ResponseEntity.notFound().build();
    }

    // Terminer un trajet par adresse MAC
    @PostMapping("/mac/{macAddress}/end-trip")
    public ResponseEntity<?> endTrip(@PathVariable String macAddress) {
        Bus bus = busService.endTrip(macAddress);
        if (bus != null) {
            return ResponseEntity.ok("Trajet terminé avec succès.");
        }
        return ResponseEntity.notFound().build();
    }

    // Mettre à jour uniquement la destination d'un bus
    @PutMapping("/mac/{macAddress}/update-destination")
    public ResponseEntity<?> updateLastDestination(
            @PathVariable String macAddress,
            @RequestParam String lastDestination) {
        Bus bus = busService.updateLastDestination(macAddress, lastDestination);
        if (bus != null) {
            return ResponseEntity.ok("Destination mise à jour avec succès.");
        }
        return ResponseEntity.notFound().build();
    }

    // Créer une nouvelle destination si elle n'existe pas
    @PostMapping("/mac/{macAddress}/create-destination")
    public ResponseEntity<?> createOrUpdateDestination(
            @PathVariable String macAddress,
            @RequestParam String lastDestination) {
        Bus bus = busService.createOrUpdateLastDestination(macAddress, lastDestination);
        if (bus != null) {
            return ResponseEntity.ok("Destination créée ou mise à jour avec succès.");
        }
        return ResponseEntity.notFound().build();
    }
}
