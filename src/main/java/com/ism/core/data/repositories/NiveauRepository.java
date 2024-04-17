package com.ism.core.data.repositories;

import com.ism.core.data.entities.NiveauEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NiveauRepository extends JpaRepository<NiveauEntity,Long> {
    Optional<NiveauEntity> findById(Long id);

}
