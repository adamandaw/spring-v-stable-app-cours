package com.ism.core.data.repositories;

import com.ism.core.data.entities.AnneeScolaireEntity;
import com.ism.core.data.entities.InscriptionEntity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InscriptionRepository extends JpaRepository<InscriptionEntity,Long> {

    List<InscriptionEntity> findAllByAnneeScolaireIsTrueAndClasse_Id(Long classeId);
    List<InscriptionEntity> findByAnneeScolaireAndClasse_Id(AnneeScolaireEntity anneeScolaire, Long idClasse);

}
