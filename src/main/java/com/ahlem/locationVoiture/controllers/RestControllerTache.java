package com.ahlem.locationVoiture.controllers;


import com.ahlem.locationVoiture.models.Projet;
import com.ahlem.locationVoiture.models.Tache;
import com.ahlem.locationVoiture.models.User;
import com.ahlem.locationVoiture.security.services.UserDetailsServiceImpl;
import com.ahlem.locationVoiture.service.ProjetServiceImp;
import com.ahlem.locationVoiture.service.TacheServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tache")
public class RestControllerTache {
    @Autowired
    private TacheServiceImp tacheServiceImp;
    @Autowired
    private ProjetServiceImp projetServiceImp;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @PreAuthorize( "hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping("/save/{idpro}/{idempl}")
    public Tache saveTache(@RequestBody Tache tache, @PathVariable Long idpro, @PathVariable Long idempl) {
        Projet m1 = projetServiceImp.getProjetByID(idpro);
        User m2= userDetailsServiceImpl.getUserByID(idempl);
        tache.setProjet(m1);
        tache.setUser(m2);
        return tacheServiceImp.createTache(tache);
    }
    @PreAuthorize( "hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/all")
    public List<Tache> getAllTaches() {
        return tacheServiceImp.getAllTaches();
    }

    @PreAuthorize( "hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
    @GetMapping("/getone/{id}")
    public Tache getoneTache(@PathVariable Long id) {
        return tacheServiceImp.getTacheByID(id);
    }

    @PreAuthorize( "hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/update/{id}")
    public Tache updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        Tache c1 = tacheServiceImp.getTacheByID(id);
        if (c1 != null) {
            tache.setId(id);
            return tacheServiceImp.updateTacheByID(tache);
        } else {
            throw new RuntimeException("Failed ! ");
        }
    }
    @PreAuthorize( "hasRole('EMPLOYEE')")
    @PutMapping("/update/Status/{id}")
    public Tache updateTacheStatus(@PathVariable Long id, @RequestBody String status) {
        Tache tache = tacheServiceImp.getTacheByID(id);



        if (tache != null) {
            tache.setStatus(status);
            return tacheServiceImp.updateTacheByID(tache);
        } else {
            throw new RuntimeException("Tache not found with ID: " + id);
        }
    }




    @PreAuthorize( "hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTache(@PathVariable("id") long id) {
        try {
            tacheServiceImp.deleteTache(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
