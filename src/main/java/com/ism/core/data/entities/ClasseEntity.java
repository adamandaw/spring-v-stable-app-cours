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

@Table(name = "classes")
public class ClasseEntity  extends  AbstractEntity{

    @OneToMany(mappedBy = "classe")
    List<ParticipationCoursEntity> participationCours;

    @OneToMany(mappedBy = "classe")
    List<ProfesseurClasseEntity> professeurClasses;

    @ManyToOne
    private CoursEntity cour;
    @ManyToOne
    private NiveauEntity niveau;

    @ManyToOne
    private FiliereEntity filiere;

    @OneToMany(mappedBy = "classe")
    private List<InscriptionEntity> inscriptions;

//    @OneToMany(mappedBy = "classe")
//    private List<EtudiantEntity> etudiants;

    @Column(unique = false,nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "classe")
    private  List<ClasseModuleEntity> modules;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean coursIsOpen;

    public ClasseEntity(){
        this.coursIsOpen=false;
  }

    @Override
    public String toString() {
        return "ClasseEntity{" +
                "cour=" + cour +
                ", niveau=" + niveau +
                ", filiere=" + filiere +
                ", libelle='" + libelle + '\'' +
                ", coursIsOpen=" + coursIsOpen +
                "} " + super.toString();
    }
}
