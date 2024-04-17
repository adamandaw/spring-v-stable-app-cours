package com.ism.core.data.fixtures;

import com.github.javafaker.Faker;
import com.ism.core.data.entities.FiliereEntity;
import com.ism.core.data.entities.ModuleEntity;
import com.ism.core.data.enums.EFiliere;
import com.ism.core.data.repositories.FiliereRepository;
import com.ism.core.data.repositories.ModulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
@Order(8)
public class ModulesFixtures implements CommandLineRunner {
    private  final ModulesRepository modulesRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 1; i <= 10; i++) {
            ModuleEntity module=new ModuleEntity();
            module.setLibelle(faker.educator().course());

            modulesRepository.save(module);
        }

    }
}
