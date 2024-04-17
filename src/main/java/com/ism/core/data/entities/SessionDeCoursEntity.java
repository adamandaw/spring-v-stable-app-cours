package com.ism.core.data.entities;

import com.ism.core.data.enums.ETypeSession;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Data

@AllArgsConstructor

@Table(name = "session_cours")
@Builder(builderMethodName = "builder")
public class SessionDeCoursEntity extends  AbstractEntity{
    private String myField;

    public static SessionDeCoursEntityBuilder builder() {
        return new SessionDeCoursEntityBuilder().myField("defaultValue");
    }

    @OneToMany(mappedBy = "sessionDeCour")
    List<ParticipationCoursEntity> participationCours;


//    @OneToOne(cascade = CascadeType.ALL,optional = true,fetch = FetchType.LAZY)
//    @JoinColumn(nullable = true,unique = false,name = "salle_id")
//    private SalleEntity salle;
    @ManyToOne
    private SalleEntity salle;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    private LocalTime debutHeure;
    private LocalTime finHeure;

    private Integer dureeHeure;
    private String  typeSession;

    private Boolean isCompleted=false;

    @ManyToOne
    @JoinColumn(name = "cour_id",unique = false)
    private CoursEntity cour;
    public SessionDeCoursEntity(){
        this.isCompleted=false;
    }


    @Override
    public String toString() {
        return "SessionDeCoursEntity{" +
                ", salle=" + salle +
                ", date=" + date +
                ", debutHeure=" + debutHeure +
                ", finHeure=" + finHeure +
                ", dureeHeure=" + dureeHeure +
                ", typeSession=" + typeSession +
                ", isCompleted=" + isCompleted +
                ", isCompleted=" + cour +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionDeCoursEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(participationCours, that.participationCours) && Objects.equals(salle, that.salle) && Objects.equals(date, that.date) && Objects.equals(debutHeure, that.debutHeure) && Objects.equals(finHeure, that.finHeure) && Objects.equals(dureeHeure, that.dureeHeure) && Objects.equals(typeSession, that.typeSession) && Objects.equals(isCompleted, that.isCompleted) && Objects.equals(cour, that.cour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), participationCours, salle, date, debutHeure, finHeure, dureeHeure, typeSession, isCompleted, cour);
    }
}
