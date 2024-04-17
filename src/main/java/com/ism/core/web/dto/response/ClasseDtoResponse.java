package com.ism.core.web.dto.response;

import com.ism.core.data.entities.*;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClasseDtoResponse {
    private String libelle;
    private Long id;
    public static ClasseDtoResponse toDto(ClasseEntity classe){

        return  ClasseDtoResponse.builder()
                .id(classe.getId())
                .libelle(classe.getNiveau().getLibelle().name() + " " +classe.getFiliere().getLibelle().name())
                .build();
    }
}
