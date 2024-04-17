package com.ism.core.web.dto.request;

import com.ism.core.data.entities.*;
import com.ism.core.data.enums.ESemestre;
import com.ism.core.data.enums.ETypeSession;
import com.ism.core.web.dto.response.ClasseDtoResponse;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


@Data
@AllArgsConstructor
@Builder
public class SessionCoursDtoRequest {

    private Long id;

    private SalleEntity salle;
    private Integer dureeHeure;
    private String typeSession;
    private CoursEntity cours;
    @Future(message = "Entrez une date valide.")
    private LocalDate date;

    @NotNull(message = "Champ requis.")
    private LocalTime debutHeure;

    @NotNull(message = "Champ requis.")
    private LocalTime finHeure;


    @NotNull(message = "Selectionnez une classe.")
    private ArrayList<ParticipationCoursEntity> participations;

    public SessionDeCoursEntity toEntity(){
        return  SessionDeCoursEntity.builder()
                .salle(new SalleEntity())
                .participationCours(participations)
                .cour(new CoursEntity())
                .date(date)
                .debutHeure(debutHeure)
                .finHeure(finHeure)
                .dureeHeure(dureeHeure)
                .typeSession(typeSession)
                .build();
    }
}
