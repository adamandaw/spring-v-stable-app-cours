package com.ism.core.data.repositories;

import com.ism.core.data.entities.SalleEntity;
import com.ism.core.data.entities.SemestreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SallesRepository extends JpaRepository<SalleEntity,Long> {
    List<SalleEntity> findAll();

    //SalleEntity findBy(SalleEntity salle);
}
