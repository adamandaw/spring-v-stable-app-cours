package com.ism.core.data.repositories;

import com.ism.core.data.entities.ClasseEntity;
import com.ism.core.data.entities.NiveauEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClasseRepository extends JpaRepository<ClasseEntity,Long> {
    List<ClasseEntity> findAll();
    Optional<ClasseEntity> findById(Long id);
    Page<ClasseEntity> findByCoursIsOpenIsFalse(Pageable pageable);

    List<ClasseEntity> findAllByNiveauAndCoursIsOpenIsTrue(NiveauEntity niveau);
   
}
