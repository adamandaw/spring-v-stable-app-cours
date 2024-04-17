package com.ism.core.data.fixtures;

import com.ism.core.data.entities.NiveauEntity;
import com.ism.core.data.entities.SemestreEntity;
import com.ism.core.data.enums.ENiveau;
import com.ism.core.data.enums.ESemestre;
import com.ism.core.data.repositories.NiveauRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
@RequiredArgsConstructor
@Order(1)
public class NiveauFixtures implements CommandLineRunner {
    private  final NiveauRepository niveauRepository;
    private ESemestre semestre;
    @Override
    public void run(String... args) throws Exception {

        for (ENiveau eniveau : ENiveau.values()) {
            NiveauEntity niveau = new NiveauEntity();
            niveau.setLibelle(ENiveau.valueOf(eniveau.name()));
            niveauRepository.save(niveau);
        }



    }
}
