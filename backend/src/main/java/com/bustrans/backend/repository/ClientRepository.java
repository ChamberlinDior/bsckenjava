package com.bustrans.backend.repository;

import com.bustrans.backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByRfid(String rfid);

    Optional<Client> findByNumClient(String numClient);
}
