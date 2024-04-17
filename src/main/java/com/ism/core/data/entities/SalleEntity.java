package com.ism.core.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "salles")
@Builder
public class SalleEntity extends  AbstractEntity{
    private Long nbrPlace;

    @Column(unique = true,nullable = false)
    private String libelle;

    @Column(unique = true,nullable = false)
    private String numero;

    //@OneToOne(mappedBy = "salle")
    @OneToMany(mappedBy = "salle")
    private List<SessionDeCoursEntity> sessionsDeCours;
//    private SessionDeCoursEntity sessionDeCours;

    @Override
    public String toString() {
        return "SalleEntity{" +
                "nbrPlace=" + nbrPlace +
                ", libelle='" + libelle + '\'' +
                ", numero='" + numero + '\'' +

                "} " + super.toString();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof SalleEntity that)) return false;
//        if (!super.equals(o)) return false;
//        return Objects.equals(nbrPlace, that.nbrPlace) && Objects.equals(libelle, that.libelle) && Objects.equals(numero, that.numero) && Objects.equals(sessionsDeCours, that.sessionsDeCours);
//    }

    //    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), nbrPlace, libelle, numero, sessionDeCours);
//    }
}
