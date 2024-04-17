package com.ism.core.data.fixtures;

import com.ism.core.data.entities.*;
import com.ism.core.data.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;

//@Component
@RequiredArgsConstructor
@Order(9)
public class ClasseModuleFixtures implements CommandLineRunner {
    private  final ClasseRepository classeRepository;
    private  final ModulesRepository modulesRepository;
    private  final ClasseModuleRepository classeModuleRepository;
    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        for (int i = 1; i <20 ; i++) {
            Long refModule =random.nextLong(9) + 1;
            Long refClasse = random.nextLong(3) + 1;
            ClasseModuleEntity classeModule=new ClasseModuleEntity();
            ModuleEntity moduleById = modulesRepository.findById(refModule).orElseThrow();
            ClasseEntity classeById = classeRepository.findById(refClasse).orElseThrow();

            classeModule.setClasse(classeById);
            classeModule.setModule(moduleById);
            classeModuleRepository.save(classeModule);
        }
        }


    }

