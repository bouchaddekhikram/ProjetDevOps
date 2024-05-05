package com.ahlem.locationVoiture.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "dateDebut")
    private Date dateDebut;
    @Column(name = "dateFin")
    private Date dateFin;
    @Column(name = "status")
    private String status;
    @ManyToOne
    Projet projet;
    @ManyToOne
    User user;
}
