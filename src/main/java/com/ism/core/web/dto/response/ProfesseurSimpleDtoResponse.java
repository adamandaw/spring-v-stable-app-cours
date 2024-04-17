package com.ism.core.web.dto.response;

import com.ism.core.data.entities.ProfesseurEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfesseurSimpleDtoResponse {
    private Long id;
    private String nomComplet;

    public static ProfesseurSimpleDtoResponse toDto(ProfesseurEntity prof){

        return  ProfesseurSimpleDtoResponse.builder()
                .id(prof.getId()).
                nomComplet(prof.getNom()+" "+prof.getPrenom().toString()).build();
    }
}
