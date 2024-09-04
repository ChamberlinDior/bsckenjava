package com.bustrans.backend.repository;

import com.bustrans.backend.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    // Méthode pour trouver un bus par son numéro de plaque (matricule)
    Bus findByMatricule(String matricule);
}
