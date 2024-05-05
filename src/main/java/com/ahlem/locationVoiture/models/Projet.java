package com.ahlem.locationVoiture.models;
import com.ahlem.locationVoiture.models.Tache;
import com.ahlem.locationVoiture.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dateDebut")
    private Date dateDebut;
    @Column(name = "dateFin")
    private Date dateFin;
    @Column(name = "nomProjet")
    private String nomProjet;
    @Column(name = "description")
    private String description;

    @ManyToOne
    private User user ;
    @JsonIgnore
    @OneToMany(mappedBy = "projet")
    private List<Tache> taches = new ArrayList<Tache>();



}
