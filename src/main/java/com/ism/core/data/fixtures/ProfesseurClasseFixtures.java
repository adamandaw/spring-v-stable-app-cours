package com.ism.core.data.fixtures;

import com.ism.core.data.entities.*;
import com.ism.core.data.enums.EFiliere;
import com.ism.core.data.repositories.ClasseRepository;
import com.ism.core.data.repositories.FiliereRepository;
import com.ism.core.data.repositories.ProfesseurClasseRepository;
import com.ism.core.data.repositories.ProfesseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

//@Component
@RequiredArgsConstructor
@Order(11)
public class ProfesseurClasseFixtures implements CommandLineRunner {
    private  final ProfesseurClasseRepository professeurClasseRepository;
    private  final ProfesseurRepository professeurRepository;
    private  final ClasseRepository classeRepository;
    

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        for (int i = 1; i <20 ; i++) {
            Long refProf =random.nextLong(9) + 1;
            Long refClasse = random.nextLong(3) + 1;
            ProfesseurClasseEntity profClasse=new ProfesseurClasseEntity();
            ProfesseurEntity professeurById = professeurRepository.findById(refProf).orElseThrow();
            ClasseEntity classeById = classeRepository.findById(refClasse).orElseThrow();

            profClasse.setClasse(classeById);
            profClasse.setProfesseur(professeurById);

            professeurClasseRepository.save(profClasse);
        }

    }
}
