package com.ism.core.services.impl;

import com.ism.core.data.entities.SalleEntity;
import com.ism.core.data.repositories.SallesRepository;
import com.ism.core.services.SalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SalleServiceImpl implements SalleService {
    private  final SallesRepository sallesRepository;
    @Override
    public List<SalleEntity> getAllSalles() {
        return sallesRepository.findAll();
    }
}
