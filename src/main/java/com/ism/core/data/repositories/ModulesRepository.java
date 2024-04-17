package com.ism.core.data.repositories;

import com.ism.core.data.entities.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModulesRepository extends JpaRepository<ModuleEntity,Long> {
    List<ModuleEntity> findAll();
    Optional<ModuleEntity> findById(Long id);
}
