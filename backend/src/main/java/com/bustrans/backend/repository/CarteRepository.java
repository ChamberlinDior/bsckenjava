package com.bustrans.backend.repository;

import com.bustrans.backend.model.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarteRepository extends JpaRepository<Carte, Long> {
    List<Carte> findByClientId(Long clientId);
    Carte findByRfid(String rfid);
}
