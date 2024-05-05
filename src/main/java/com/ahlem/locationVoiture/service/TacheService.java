package com.ahlem.locationVoiture.service;

import com.ahlem.locationVoiture.models.Tache;

import java.util.List;

public interface TacheService {
    List<Tache> getAllTaches();

    Tache createTache(Tache tache);

    Tache getTacheByID(Long id);

    Tache updateTacheByID(Tache tache);

    void deleteTache(Long id);
}
