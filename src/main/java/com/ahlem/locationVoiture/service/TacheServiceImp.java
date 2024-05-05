package com.ahlem.locationVoiture.service;

import com.ahlem.locationVoiture.models.Tache;
import com.ahlem.locationVoiture.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TacheServiceImp implements TacheService {
    @Autowired
    private TacheRepository tacheRepository;

    @Override
    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    @Override
    public Tache createTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    @Override
    public Tache getTacheByID(Long id) {
        return tacheRepository.findById(id).get();
    }

    @Override
    public Tache updateTacheByID(Tache tache) {
        return tacheRepository.saveAndFlush(tache);
    }

    @Override
    public void deleteTache(Long id) {tacheRepository.deleteById(id);}

}
