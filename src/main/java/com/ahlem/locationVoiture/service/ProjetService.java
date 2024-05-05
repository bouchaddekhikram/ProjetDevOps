package com.ahlem.locationVoiture.service;

import com.ahlem.locationVoiture.models.Projet;

import java.util.List;

public interface ProjetService {


    List<Projet> getAllProjets();

    Projet createProjet(Projet projet);

    Projet getProjetByID(Long id);

    Projet updateProjetByID(Projet projet);

    void deleteProjet(Long id);
}
