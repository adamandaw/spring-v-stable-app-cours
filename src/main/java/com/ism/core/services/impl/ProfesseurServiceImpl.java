package com.ism.core.services.impl;

import com.ism.core.data.entities.ProfesseurEntity;
import com.ism.core.data.entities.SemestreEntity;
import com.ism.core.data.repositories.ProfesseurRepository;
import com.ism.core.services.ProfesseurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProfesseurServiceImpl implements ProfesseurService {
    private final ProfesseurRepository professeurRepository;
    @Override
    public List<ProfesseurEntity> getAllProfesseur() {
        return professeurRepository.findAll();
    }
}
