package com.ism.core.web.dto.request;

import com.ism.core.data.entities.*;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class CoursDtoRequest {

    private Long id;
    @NotNull(message = "Champ requis.")
    private int nbrHeureGlobal;

    @NotNull(message = "Ce Champ est requis.")
    private SemestreEntity semestre;
    
    private AnneeScolaireEntity anneeScolaire;

    @NotNull(message = "Ce Champ est requis.")
    private ModuleEntity module;

    @NotNull(message = "Champ requis.")
    private ProfesseurEntity professeur;

    public CoursEntity toEntity(){
        return  CoursEntity.builder()
                .nbrHeureGlobal(nbrHeureGlobal)
                .semestre(new SemestreEntity())
                .anneeScolaire(new AnneeScolaireEntity())
                .module(new ModuleEntity())
                .professeur(new ProfesseurEntity())
                .build();
    }
}
