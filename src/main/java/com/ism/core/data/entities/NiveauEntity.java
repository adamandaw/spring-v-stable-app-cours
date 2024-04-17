package com.ism.core.data.entities;

import com.ism.core.data.enums.ENiveau;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "niveaux")
public class NiveauEntity extends  AbstractEntity{
    @Enumerated(value = EnumType.STRING)
    private ENiveau libelle;

    @OneToMany(mappedBy = "niveau")
    private List<ClasseEntity> classes;

    @OneToMany(mappedBy = "niveauSemestre")
    private List<SemestreEntity> semestres;

    @Override
    public String toString() {
        return "NiveauEntity{" +
                "libelle=" + libelle +
                '}';
    }
}
