package com.ism.core.web.dto.response;

import com.ism.core.data.entities.SessionDeCoursEntity;
import com.ism.core.data.enums.ETypeSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionCoursDtoResponse {
    private String libelleSalle;
    private String date;
    private String debutHeure;
    private String finHeure;
    private String typeSession;
    private Integer duree;
    private Long id;
    public static SessionCoursDtoResponse toDto(SessionDeCoursEntity session){
        Duration duration=Duration.between(session.getDebutHeure(),session.getFinHeure());

        return  SessionCoursDtoResponse.builder()
                .id(session.getId())
                .typeSession(String.valueOf(session.getTypeSession()))
                .date(session.getDate().toString())
                .debutHeure(session.getDebutHeure().toString())
                .finHeure(session.getFinHeure().toString())
                .duree(Math.toIntExact(duration.toHours()))

                .build();
    }

}
