package com.ism.core.data.fixtures;

import com.ism.core.data.entities.ClasseEntity;
import com.ism.core.data.entities.FiliereEntity;
import com.ism.core.data.entities.NiveauEntity;
import com.ism.core.data.enums.EFiliere;
import com.ism.core.data.enums.ENiveau;
import com.ism.core.data.repositories.ClasseRepository;
import com.ism.core.data.repositories.FiliereRepository;
import com.ism.core.data.repositories.NiveauRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
//@Component
@RequiredArgsConstructor
@Order(4)
public class ClassesFixtures implements CommandLineRunner {
    private  final FiliereRepository filiereRepository;
    private  final ClasseRepository classeRepository;
    private  final NiveauRepository niveauRepository;

    @Override
    public void run(String... args) throws Exception {
        NiveauEntity niveau = niveauRepository.findById(3L).orElseThrow();
        List<FiliereEntity> filieres = filiereRepository.findAll();
        for (int i = 1; i < 5; i++) {
            ClasseEntity classe=new ClasseEntity();
            classe.setNiveau(niveau);
            classe.setFiliere(filieres.get(i-1));
            classe.setLibelle(niveau.getLibelle().name() +" " + filieres.get(i-1).getLibelle());
               classe.setCoursIsOpen(classe.getLibelle()=="L3 CDSD");
               classe.setCoursIsOpen(classe.getLibelle()=="L3 MAE");
                classeRepository.save(classe);
            }
        }


    }

