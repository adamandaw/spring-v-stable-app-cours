package com.ism.core.web.dto.response;

import com.ism.core.data.entities.CoursEntity;
import com.ism.core.data.entities.ModuleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoursDtoResponse {
    private Long id;
    private String libelle;
    private String semestre;
    private Integer heureGlobale;
    private Integer heurePlanifier;
    private Integer heureEffectuer;
    private String professeurNomComplet;


    public static CoursDtoResponse toDto(CoursEntity cours){

        return  CoursDtoResponse.builder()
                .id(cours.getId())
                .libelle(cours.getModule().getLibelle().toString())
                .semestre(cours.getSemestre().getLibelle().name())
                .heureGlobale(cours.getNbrHeureGlobal())
                .heurePlanifier(cours.getNbrHeurePlanifier())
                .heureEffectuer((cours.getNbrHeureEffectuer()))
                .professeurNomComplet(cours.getProfesseur().getNom())
                .build();
    }
}
