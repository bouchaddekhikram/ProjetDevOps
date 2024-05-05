package com.gestion.stage.controllers;

import com.gestion.stage.models.Candidature;
import com.gestion.stage.models.Offres;
import com.gestion.stage.models.User;
import com.gestion.stage.security.services.UserDetailsServiceImpl;
import com.gestion.stage.service.CandidatureServiceImp;
import com.gestion.stage.service.OffresServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("/api/Candidature")
    public class CandidatureController {
        @Autowired
        private CandidatureServiceImp  candidatureServiceImp ;

        @Autowired
        private UserDetailsServiceImpl userDetailsServiceImpl;
        @Autowired
        private OffresServiceImp offresServiceImp;


        //    @PreAuthorize( "hasRole('RH')")
        @PostMapping("/save/{idST}/{idOff}")
        public Candidature saveCandidateur(@RequestBody Candidature candidature, @PathVariable Long idST, @PathVariable Long idOff) {
            User m1= userDetailsServiceImpl.getUserByID(idST);
            Offres m2 =offresServiceImp.getOffreByID(idOff);
            candidature.setUser(m1);
            candidature.setOffres(m2);
            return candidatureServiceImp.createCandidature(candidature);
        }

    @GetMapping("/all")
    public List<Candidature> getAllCandidature() {
        return candidatureServiceImp.getAllCandidatures();
    }

    @GetMapping("/getone/{id}")
    public Candidature getoneCandidature(@PathVariable Long id) {
        return candidatureServiceImp.getCandidatureByID(id);
    }

    @PutMapping("/update/{id}")
    public Candidature updateCandidature(@PathVariable Long id, @RequestBody Candidature candidature) {
        Candidature C1 = candidatureServiceImp.getCandidatureByID(id);
        if (C1 != null) {
            candidature.setId(id);
            return candidatureServiceImp.updateCandidatureByID(candidature);
        } else {
            throw new RuntimeException("Failed to update! Candidature not found with ID: " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCandidature(@PathVariable("id") long id) {
        try {
            candidatureServiceImp.deleteCandidature(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
