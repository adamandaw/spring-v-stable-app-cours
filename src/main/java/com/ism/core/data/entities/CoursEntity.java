package com.ism.core.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "cours")
@Builder
public class CoursEntity extends  AbstractEntity{
    @OneToMany(mappedBy = "cour")
    private List<SessionDeCoursEntity> sessionsCours;


    private Integer nbrHeureGlobal;

    private  Integer nbrHeurePlanifier;

    private Integer nbrHeureEffectuer;

    private Boolean isDone;
    @ManyToOne
    private SemestreEntity semestre;
    @ManyToOne
    private AnneeScolaireEntity anneeScolaire;
    @ManyToOne
    private ModuleEntity module;
    @ManyToOne
    private ProfesseurEntity professeur;

    @OneToMany(mappedBy = "cour")
    private List<ClasseEntity> classes;
    public CoursEntity(){
        this.nbrHeureEffectuer=0;
        this.nbrHeurePlanifier=0;
        this.isDone=false;
    }

    @Override
    public String toString() {
        return "CoursEntity{" +
                "nbrHeureGlobal=" + nbrHeureGlobal +
                ", nbrHeurePlanifier=" + nbrHeurePlanifier +
                ", nbrHeureEffectuer=" + nbrHeureEffectuer +
                ", isDone=" + isDone +
                ", semestre=" + semestre +
                ", anneeScolaire=" + anneeScolaire +
                ", module=" + module +
                ", professeur=" + professeur +
                "} " + super.toString();
    }

}
