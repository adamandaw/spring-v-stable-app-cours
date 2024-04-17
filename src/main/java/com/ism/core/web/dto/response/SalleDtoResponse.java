package com.ism.core.web.dto.response;

import com.ism.core.data.entities.SalleEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalleDtoResponse {
    private String libelle;
    private Long id;
    private Long nbrPlace;
    
    public static SalleDtoResponse toDto(SalleEntity salle){

        return  SalleDtoResponse.builder()
                .id(salle.getId())
                .libelle(salle.getLibelle() + " " + salle.getNumero())
                .nbrPlace(salle.getNbrPlace())
                .build();
    }
}
