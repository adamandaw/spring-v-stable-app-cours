package com.ism.core.data.repositories;

import com.ism.core.data.entities.AnneeScolaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaireEntity,Long> {
    AnneeScolaireEntity findByIsActiveIsTrue();
}
