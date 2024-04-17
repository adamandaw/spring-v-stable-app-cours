package com.ism.core.web.dto.response;

import com.ism.core.data.entities.SemestreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SemetreDtoResponse {
    private Long id;
    private String libelle;

    public static SemetreDtoResponse toDto(SemestreEntity semestre){

        return  SemetreDtoResponse.builder()
                .id(semestre.getId()).
                libelle(semestre.getLibelle().toString()).build();
    }
}
