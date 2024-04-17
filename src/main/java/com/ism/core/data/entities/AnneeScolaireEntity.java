package com.ism.core.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "annees_scolaires")
public class AnneeScolaireEntity extends  AbstractEntity{
    @Column(unique = false,nullable = false)
    private String libelle;

    private Boolean isActive;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateDeCloturation;

    @OneToMany(mappedBy = "anneeScolaire")
    private List<InscriptionEntity> inscriptions;

    @OneToMany(mappedBy = "anneeScolaire")
    private List<CoursEntity> cours;

    @Override
    public String toString() {
        return "AnneeScolaireEntity{" +
                "libelle='" + libelle + '\'' +
                ", isActive=" + isActive +
                ", dateDeCloturation=" + dateDeCloturation +
                "} " + super.toString();
    }
}
