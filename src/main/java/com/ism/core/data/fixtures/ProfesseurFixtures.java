package com.ism.core.data.fixtures;

import com.github.javafaker.Faker;
import com.ism.core.data.entities.ProfesseurEntity;
import com.ism.core.data.enums.EGrade;
import com.ism.core.data.enums.ESexe;
import com.ism.core.data.enums.ESpecialite;
import com.ism.core.data.repositories.ProfesseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
@Order(10)
public class ProfesseurFixtures implements CommandLineRunner {
    private  final ProfesseurRepository professeurRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();
        for (int i = 1; i <=10 ; i++) {
            ProfesseurEntity prof= new ProfesseurEntity();
            prof.setNom(faker.funnyName().name());
            prof.setPrenom(faker.funnyName().name());
            prof.setGrade(EGrade.values()[(int)  Math.floor(Math.random() * EGrade.values().length)]);
//            prof.setSpecialite(ESpecialite.values()[(int) (Math.random() * ESpecialite.values().length)]);
            prof.setSpecialite(ESpecialite.values()[(int) Math.floor(Math.random() * ESpecialite.values().length)]);
            professeurRepository.save(prof);

        }

    }
}
