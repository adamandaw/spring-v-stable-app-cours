package com.ism.core.data.repositories;

import com.ism.core.data.entities.EtudiantEntity;
import com.ism.core.data.entities.ProfesseurEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesseurRepository extends JpaRepository<ProfesseurEntity,Long> {

    @Override
    List<ProfesseurEntity> findAll();
}
