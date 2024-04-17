package com.ism.core.services;

import com.ism.core.data.entities.ClasseEntity;
import com.ism.core.data.entities.NiveauEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClasseService {

    Page<ClasseEntity> getAllClasseNotActive(Pageable pageable);

    Optional<ClasseEntity> getClasse(Long id);

    List<ClasseEntity> getClassesByNiveau(NiveauEntity niveau);

}
