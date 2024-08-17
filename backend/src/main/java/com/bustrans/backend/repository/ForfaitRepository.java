package com.bustrans.backend.repository;

import com.bustrans.backend.model.Forfait;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForfaitRepository extends JpaRepository<Forfait, Long> {
    // Custom query methods can be added here if needed
}
