package com.ahlem.locationVoiture.repository;

import com.ahlem.locationVoiture.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache,Long> {
}
