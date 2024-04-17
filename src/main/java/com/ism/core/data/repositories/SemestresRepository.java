package com.ism.core.data.repositories;

import com.ism.core.data.entities.FiliereEntity;
import com.ism.core.data.entities.SemestreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemestresRepository extends JpaRepository<SemestreEntity,Long> {
    List<SemestreEntity> findAll();

}
