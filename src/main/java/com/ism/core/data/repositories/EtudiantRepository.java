package com.ism.core.data.repositories;

import com.ism.core.data.entities.ClasseEntity;
import com.ism.core.data.entities.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<EtudiantEntity,Long> {

}
