package com.ism.core.web.dto.response;

import com.ism.core.data.entities.ModuleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleDtoResponse {
    private Long id;
    private String libelle;

    public static ModuleDtoResponse toDto(ModuleEntity mod){

        return  ModuleDtoResponse.builder()
                .id(mod.getId()).
                libelle(mod.getLibelle().toString()).build();
    }
}
