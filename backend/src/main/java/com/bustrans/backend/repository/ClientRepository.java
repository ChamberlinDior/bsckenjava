package com.bustrans.backend.repository;

import com.bustrans.backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // Rechercher un client par RFID
    Optional<Client> findByRfid(String rfid);

    // Rechercher un client par numéro de client
    Optional<Client> findByNumClient(String numClient);
}
