package com.ism.core.data.fixtures;

import com.github.javafaker.Faker;
import com.ism.core.data.entities.EtudiantEntity;
import com.ism.core.data.entities.NiveauEntity;
import com.ism.core.data.entities.SemestreEntity;
import com.ism.core.data.enums.ESemestre;
import com.ism.core.data.enums.ESexe;
import com.ism.core.data.repositories.EtudiantRepository;
import com.ism.core.data.repositories.NiveauRepository;
import com.ism.core.data.repositories.SemestresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

//@Component
@RequiredArgsConstructor
@Order(6)
public class EtudiantFixtures implements CommandLineRunner {
    private  final EtudiantRepository etudiantRepository;

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        Faker faker = new Faker();
        for (int i = 1; i <=10 ; i++) {
            EtudiantEntity etudiant= new EtudiantEntity();
            etudiant.setNom(faker.funnyName().name());
            etudiant.setPrenom(faker.funnyName().name());
            etudiant.setSexe(ESexe.values()[(int) (Math.random() * ESexe.values().length)]);
            LocalDate date = LocalDate.of(random.nextInt(1999,2004),
                    random.nextInt(1,12),
                    random.nextInt(1,27));
            etudiant.setDateNaissance(date);
            etudiant.setTelephone(faker.phoneNumber().cellPhone());
            etudiantRepository.save(etudiant);

        }

    }
}
