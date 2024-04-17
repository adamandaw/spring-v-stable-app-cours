package com.ism.core.services.impl;

import com.ism.core.data.entities.ClasseEntity;
import com.ism.core.data.entities.NiveauEntity;
import com.ism.core.data.repositories.ClasseRepository;
import com.ism.core.services.ClasseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClasseServiceImpl implements ClasseService {
    private  final ClasseRepository classeRepository;
    @Override
    public Page<ClasseEntity> getAllClasseNotActive(Pageable pageable) {
        return classeRepository.findByCoursIsOpenIsFalse(pageable);
    }

    @Override
    public Optional<ClasseEntity> getClasse(Long id) {
        return classeRepository.findById(id);
    }

    @Override
    public List<ClasseEntity> getClassesByNiveau(NiveauEntity niveau) {
        return classeRepository.findAllByNiveauAndCoursIsOpenIsTrue(niveau);
    }


}
