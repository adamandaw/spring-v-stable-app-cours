package com.ism.core.services.impl;

import com.ism.core.data.entities.ModuleEntity;
import com.ism.core.data.repositories.ModulesRepository;
import com.ism.core.services.IModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class IModuleServiceImpl implements IModuleService {
    private final ModulesRepository modulesRepository;
    @Override
    public List<ModuleEntity> getAll() {
        return modulesRepository.findAll();
    }

    @Override
    public ModuleEntity getById(Long id) {
        return modulesRepository.findById(id).get();
    }
}
