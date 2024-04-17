package com.ism.core.data.repositories;

import com.ism.core.data.entities.AnneeScolaireEntity;
import com.ism.core.data.entities.ClasseModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseModuleRepository extends JpaRepository<ClasseModuleEntity,Long> {

}
