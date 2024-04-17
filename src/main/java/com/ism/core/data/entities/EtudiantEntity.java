package com.ism.core.data.entities;

import com.ism.core.data.enums.ESexe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "etudiants")
public class EtudiantEntity extends AbstractPersonEntity{

//    @ManyToOne
//    private ClasseEntity classe;

    @Column(unique = true,nullable = false)
    private String telephone;

    @Enumerated(value = EnumType.STRING)
    private ESexe sexe;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateNaissance;

//    etudiant

    @OneToMany(mappedBy = "etudiant")
    private List<InscriptionEntity> inscriptions;

    @Override
    public String toString() {
        return "EtudiantEntity{" +
                "telephone='" + telephone + '\'' +
                ", sexe=" + sexe +
                ", dateNaissance=" + dateNaissance +
                "} " + super.toString();
    }
}
