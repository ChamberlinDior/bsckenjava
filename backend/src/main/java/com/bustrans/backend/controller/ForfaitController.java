package com.bustrans.backend.controller;

import com.bustrans.backend.dto.ForfaitDTO;
import com.bustrans.backend.model.Forfait;
import com.bustrans.backend.service.ForfaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forfaits")
public class ForfaitController {

    @Autowired
    private ForfaitService forfaitService;

    // Récupérer l'historique des forfaits d'un client
    @GetMapping("/historique/{clientId}")
    public ResponseEntity<List<ForfaitDTO>> getForfaitHistory(@PathVariable Long clientId) {
        List<ForfaitDTO> forfaits = forfaitService.getForfaitHistory(clientId);
        if (forfaits.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(forfaits);
    }

    // Créer un nouveau forfait pour un client
    @PostMapping
    public ResponseEntity<Forfait> createForfait(@RequestBody ForfaitDTO forfaitDTO) {
        Forfait forfait = forfaitService.createForfait(forfaitDTO);
        return ResponseEntity.ok(forfait);
    }

    // Supprimer un forfait par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForfait(@PathVariable Long id) {
        forfaitService.deleteForfait(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer le statut d'un forfait par RFID
    @GetMapping("/status/{rfid}")
    public ResponseEntity<ForfaitDTO> getForfaitStatusByRFID(@PathVariable String rfid) {
        ForfaitDTO forfaitDTO = forfaitService.getForfaitStatusByRFID(rfid);
        if (forfaitDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(forfaitDTO);
    }
}
