package com.bustrans.backend.service;

import com.bustrans.backend.model.Forfait;
import com.bustrans.backend.repository.ForfaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForfaitService {

    @Autowired
    private ForfaitRepository forfaitRepository;

    public List<Forfait> getAllForfaits() {
        return forfaitRepository.findAll();
    }

    public Forfait saveForfait(Forfait forfait) {
        return forfaitRepository.save(forfait);
    }

    public Forfait getForfaitById(Long id) {
        return forfaitRepository.findById(id).orElse(null);
    }

    public void deleteForfait(Long id) {
        forfaitRepository.deleteById(id);
    }
}
