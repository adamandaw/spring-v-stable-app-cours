package com.ism.core.data.fixtures;

import com.ism.core.data.entities.FiliereEntity;
import com.ism.core.data.entities.NiveauEntity;
import com.ism.core.data.enums.EFiliere;
import com.ism.core.data.enums.ENiveau;
import com.ism.core.data.repositories.FiliereRepository;
import com.ism.core.data.repositories.NiveauRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
@Order(2)
public class FilieresFixtures implements CommandLineRunner {
    private  final FiliereRepository filiereRepository;

    @Override
    public void run(String... args) throws Exception {
        for (EFiliere filEnum : EFiliere.values()) {
            FiliereEntity filiere = new FiliereEntity();
            filiere.setLibelle(EFiliere.valueOf(filEnum.name()));

            filiereRepository.save(filiere);
        }

    }
}
