package com.ism.core.services.impl;

import com.ism.core.data.entities.AnneeScolaireEntity;
import com.ism.core.data.entities.CoursEntity;
import com.ism.core.data.entities.ModuleEntity;
import com.ism.core.data.entities.SemestreEntity;
import com.ism.core.data.repositories.AnneeScolaireRepository;
import com.ism.core.data.repositories.CoursRepository;
import com.ism.core.data.repositories.ModulesRepository;
import com.ism.core.data.repositories.SemestresRepository;
import com.ism.core.services.CoursService;
import com.ism.core.web.dto.request.CoursDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CoursServiceImpl implements CoursService {
    private  final SemestresRepository semestresRepository;
    private  final ModulesRepository modulesRepository;
    private final CoursRepository coursRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;

    @Override
    public CoursEntity getByModule(ModuleEntity module) {
        return coursRepository.findByModule(module);
    }

    @Override
    public int updateHeurePlanifiorByCour(Long id, int nbrHeurePlanifier) {
       return coursRepository.updateNbrHeurePlanifier(id,nbrHeurePlanifier) > 0 ? 1: 0;
    }

    @Override
    public int updateHeureEffectuer(Long id, int value) {
        coursRepository.updateNbrHeureEffectuer(id,value);
        return  1;
    }

    @Override
    public List<SemestreEntity> getAllSemestres() {
        return semestresRepository.findAll();
    }

    @Override
    public List<ModuleEntity> getAllModules() {
        return modulesRepository.findAll();
    }

    @Override
    public void store(CoursDtoRequest cours) {
        CoursEntity coursEntity=cours.toEntity();
        AnneeScolaireEntity currentYear = anneeScolaireRepository.findByIsActiveIsTrue();
         coursEntity.setAnneeScolaire(currentYear);
         coursEntity.setModule(cours.getModule());
         coursEntity.setProfesseur(cours.getProfesseur());
         coursEntity.setSemestre(cours.getSemestre());
         coursEntity.setIsDone(false);
         coursEntity.setNbrHeureEffectuer(0);
         coursEntity.setNbrHeurePlanifier(0);
        coursRepository.save(coursEntity);
    }

    @Override
    public Page<CoursEntity> getAllCoursByDoneFalse(Pageable pageable) {
        return coursRepository.findAllByIsArchivedIsFalseAndIsDoneIsFalse(pageable);
    }

    @Override
    public int setCoursToIsDone(Long id) {
        return coursRepository.updateCoursIsDone(id) > 0 ? 1 : 0;
    }

    @Override
    public CoursEntity getCourById(Long id) {
        return coursRepository.findByIdAndAndIsArchivedFalseAndIsDoneIsFalse(id);
    }
}
