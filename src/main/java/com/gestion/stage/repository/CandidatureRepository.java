package com.gestion.stage.repository;

import com.gestion.stage.models.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatureRepository extends JpaRepository<Candidature,Long> {
}
