package com.ahlem.locationVoiture.service;


import com.ahlem.locationVoiture.models.Projet;
import com.ahlem.locationVoiture.repository.ProjetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetServiceImp implements ProjetService {
    @Autowired
    private ProjetRepository projetRepository;


    @Override
    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    @Override
    public Projet createProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public Projet getProjetByID(Long id) {
        return projetRepository.findById(id).get();
    }

    @Override
    public Projet updateProjetByID(Projet projet) {
        return projetRepository.saveAndFlush(projet);
    }

    @Override
    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }
}
