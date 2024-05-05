package com.ahlem.locationVoiture.repository;

import com.ahlem.locationVoiture.models.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet,Long> {
}
