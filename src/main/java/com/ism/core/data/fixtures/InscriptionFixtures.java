package com.ism.core.data.fixtures;

import com.ism.core.data.entities.AnneeScolaireEntity;
import com.ism.core.data.entities.ClasseEntity;
import com.ism.core.data.entities.EtudiantEntity;
import com.ism.core.data.entities.InscriptionEntity;
import com.ism.core.data.repositories.AnneeScolaireRepository;
import com.ism.core.data.repositories.ClasseRepository;
import com.ism.core.data.repositories.EtudiantRepository;
import com.ism.core.data.repositories.InscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

//@Component
@RequiredArgsConstructor
@Order(7)
public class InscriptionFixtures implements CommandLineRunner {
    private final InscriptionRepository inscriptionRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;
    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;
    @Override
    public void run(String... args) throws Exception {
        List<ClasseEntity> classes = classeRepository.findAll();
        List<AnneeScolaireEntity> annees = anneeScolaireRepository.findAll();
        List<EtudiantEntity> etudiants = etudiantRepository.findAll();
        Random random = new Random();
        for (int i=1; i <= 39; i++) {
            InscriptionEntity inscription=new InscriptionEntity();
            inscription.setAnneeScolaire(annees.get(random.nextInt(1,3)));
            inscription.setEtudiant(etudiants.get(random.nextInt(1,10)));
            inscription.setClasse(classes.get(random.nextInt(1,3)));
            inscriptionRepository.save(inscription);
        }



    }
}

