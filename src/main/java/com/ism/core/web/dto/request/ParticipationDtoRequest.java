package com.ism.core.web.dto.request;

import com.ism.core.data.entities.*;
import com.ism.core.web.dto.response.ClasseDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class ParticipationDtoRequest {

    private Long id;
    private ClasseDtoResponse classe;

    public ParticipationCoursEntity toEntity(){
        return  ParticipationCoursEntity.builder()
                .classe(new ClasseEntity())
                .build();
    }
}
