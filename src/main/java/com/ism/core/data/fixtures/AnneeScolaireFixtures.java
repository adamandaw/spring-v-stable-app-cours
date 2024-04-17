package com.ism.core.data.fixtures;

import com.github.javafaker.Faker;
import com.ism.core.data.entities.AnneeScolaireEntity;
import com.ism.core.data.entities.ClasseEntity;
import com.ism.core.data.entities.FiliereEntity;
import com.ism.core.data.entities.NiveauEntity;
import com.ism.core.data.repositories.AnneeScolaireRepository;
import com.ism.core.data.repositories.ClasseRepository;
import com.ism.core.data.repositories.FiliereRepository;
import com.ism.core.data.repositories.NiveauRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

//@Component
@RequiredArgsConstructor
@Order(5)
public class AnneeScolaireFixtures implements CommandLineRunner {

    private final AnneeScolaireRepository anneeScolaireRepository;
    @Override
    public void run(String... args) throws Exception {
        for (int i=2020; i < 2024; i++) {
            AnneeScolaireEntity annee = new AnneeScolaireEntity();
            int countYear= i+ 1;
            String lib = i + "-" + String.valueOf(countYear) ;
            annee.setLibelle(lib);
            LocalDate endDate = LocalDate.of(i+1, 2, 11);
            annee.setDateDeCloturation(endDate);


            annee.setIsActive(annee.getLibelle().equals("2023-2024"));
            anneeScolaireRepository.save(annee);
        }



    }
}

