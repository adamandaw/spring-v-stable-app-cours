package com.ism.core.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "inscriptions")
public class InscriptionEntity extends AbstractEntity{
    @ManyToOne
    private AnneeScolaireEntity anneeScolaire;

//    etudiant
    @ManyToOne
    private EtudiantEntity etudiant;

    @ManyToOne
    private ClasseEntity classe;

    @Override
    public String toString() {
        return "InscriptionEntity{" +
                "anneeScolaire=" + anneeScolaire +
                ", etudiant=" + etudiant +
                ", classe=" + classe +
                "} " ;
    }
}
