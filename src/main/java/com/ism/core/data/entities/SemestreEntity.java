package com.ism.core.data.entities;

import com.ism.core.data.enums.ESemestre;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Table(name = "semestres")
@Builder
public class SemestreEntity extends  AbstractEntity{
    @Enumerated(value = EnumType.STRING)
    private ESemestre    libelle;

    @ManyToOne
    private NiveauEntity niveauSemestre;

    @OneToMany(mappedBy = "semestre")
    List<CoursEntity> cours;

    @Override
    public String toString() {
        return "SemestreEntity{" +
                "libelle=" + libelle +
                ", niveauSemestre=" + niveauSemestre +

                "} " ;
    }
}
