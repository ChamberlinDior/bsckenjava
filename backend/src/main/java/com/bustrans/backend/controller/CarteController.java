package com.bustrans.backend.controller;

import com.bustrans.backend.dto.CarteDTO;
import com.bustrans.backend.service.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartes")
public class CarteController {

    @Autowired
    private CarteService carteService;

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CarteDTO>> getCartesByClientId(@PathVariable Long clientId) {
        List<CarteDTO> cartes = carteService.getCartesByClientId(clientId);
        if (cartes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarteDTO> createCarte(@RequestBody CarteDTO carteDTO) {
        // Vérification que le rfid est bien fourni
        if (carteDTO.getRfid() == null || carteDTO.getRfid().isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try {
            CarteDTO createdCarte = carteService.createCarte(carteDTO);
            return new ResponseEntity<>(createdCarte, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
