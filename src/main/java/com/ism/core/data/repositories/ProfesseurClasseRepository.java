package com.ism.core.data.repositories;

import com.ism.core.data.entities.AnneeScolaireEntity;
import com.ism.core.data.entities.ProfesseurClasseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurClasseRepository extends JpaRepository<ProfesseurClasseEntity,Long> {

}
