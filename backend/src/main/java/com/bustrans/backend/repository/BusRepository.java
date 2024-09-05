package com.bustrans.backend.repository;

import com.bustrans.backend.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Bus findByMacAddress(String macAddress);  // Recherche d'un bus par l'adresse MAC
}