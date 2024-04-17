package com.ism.core.data.repositories;


import com.ism.core.data.entities.CoursEntity;
import com.ism.core.data.entities.ModuleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Transactional
public interface CoursRepository extends JpaRepository<CoursEntity,Long> {

    CoursEntity findByModule(ModuleEntity module);
    @Modifying
    @Query("UPDATE CoursEntity c SET c.nbrHeurePlanifier = :nbrHeurePlanifier WHERE c.id = :id")
    int updateNbrHeurePlanifier(@Param("id") Long id, @Param("nbrHeurePlanifier") int nbrHeurePlanifier);

    @Modifying
    @Query("UPDATE CoursEntity c SET c.nbrHeureEffectuer = :nbrHeureEffectuer WHERE c.id = :id")
    void updateNbrHeureEffectuer(@Param("id") Long id, @Param("nbrHeureEffectuer") int nbrHeureEffectuer);

    @Modifying
    @Query("UPDATE CoursEntity c SET c.isDone = true WHERE c.id = :id")
    int updateCoursIsDone(@Param("id") Long id);


    Page<CoursEntity> findAllByIsArchivedIsFalseAndIsDoneIsFalse(Pageable pageable);
    CoursEntity findByIdAndAndIsArchivedFalseAndIsDoneIsFalse(Long id);
    Optional<CoursEntity> findById(Long id);
}
