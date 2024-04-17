package com.ism.core.web.dto.response;

import com.ism.core.data.entities.SessionDeCoursEntity;
import com.ism.core.data.enums.ETypeSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeSessionDtoResponse {

    private String typeSession;
    private int index;
    public static TypeSessionDtoResponse toDto(ETypeSession session){

        return  TypeSessionDtoResponse.builder()
                .index(ETypeSession.values().length)
                .typeSession(ETypeSession.values().toString())
                .build();
    }
}
