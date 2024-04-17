package com.ism.core.data.entities;

import com.ism.core.data.enums.EGrade;
import com.ism.core.data.enums.ESexe;
import com.ism.core.data.enums.ESpecialite;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "professeurs")
@Builder
public class ProfesseurEntity extends AbstractPersonEntity {
    @Enumerated(value = EnumType.STRING)
    private EGrade grade;
    @Enumerated(value = EnumType.STRING)
    private ESpecialite specialite;

    @OneToMany(mappedBy = "professeur")
    List<ProfesseurClasseEntity> classes;

    @OneToMany(mappedBy = "professeur")
    private List<CoursEntity> cours;

    @Override
    public String toString() {
        return "ProfesseurEntity{" +
                "grade=" + grade +
                ", specialite=" + specialite +
                ", classes=" + classes +
                ", cours=" + cours +
                '}';
    }
}
