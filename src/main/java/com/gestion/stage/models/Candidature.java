package com.gestion.stage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dateDebut")
    private Date dateDebut;
    @Column(name = "dateFin")
    private Date dateFin;
    @Column(name = "nivEtude")
    private String nivEtude;
    @Column(name = "etablissement")
    private String etablissement;
    @Column(name = "specialite")
    private String specialite;
    @Column(name = "typeStage")
    private String typeStage;



    @JsonIgnore
    @ManyToOne
    private User user ;
    @JsonIgnore
    @ManyToOne
    private Offres offres;

}
