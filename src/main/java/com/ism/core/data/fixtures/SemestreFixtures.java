package com.ism.core.data.fixtures;

import com.ism.core.data.entities.NiveauEntity;
import com.ism.core.data.entities.SemestreEntity;
import com.ism.core.data.enums.ENiveau;
import com.ism.core.data.enums.ESemestre;
import com.ism.core.data.repositories.NiveauRepository;
import com.ism.core.data.repositories.SemestresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
//@Component
@RequiredArgsConstructor
@Order(3)
public class SemestreFixtures implements CommandLineRunner {
    private  final SemestresRepository semestresRepository;
    private  final NiveauRepository niveauRepository;
    @Override
    public void run(String... args) throws Exception {
        NiveauEntity l1 = niveauRepository.findById(1L).orElseThrow();
        NiveauEntity l2 = niveauRepository.findById(2L).orElseThrow();
        NiveauEntity l3 = niveauRepository.findById(3L).orElseThrow();
        for (ESemestre semestreEnum : ESemestre.values()) {
            SemestreEntity semestre = new SemestreEntity();
            semestre.setLibelle(ESemestre.valueOf(semestreEnum.name()));

            switch (semestreEnum) {
                case SEMESTRE_1:
                case SEMESTRE_2:
                    semestre.setNiveauSemestre(l1);
                    break;
                case SEMESTRE_3:
                case SEMESTRE_4:
                    semestre.setNiveauSemestre(l2);
                    break;
                case SEMESTRE_5:
                case SEMESTRE_6:
                    semestre.setNiveauSemestre(l3);
                    break;
                default:
                    // Faire quelque chose si nécessaire pour les autres cas
                    break;
            }
            semestresRepository.save(semestre);
        }

    }
}
