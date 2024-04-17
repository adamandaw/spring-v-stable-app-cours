package com.ism.core.data.entities;

import com.ism.core.data.enums.EFiliere;
import com.ism.core.data.enums.ENiveau;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Table(name = "filieres")
public class FiliereEntity extends  AbstractEntity{
    @Enumerated(value = EnumType.STRING)
    private EFiliere libelle;;
    @OneToMany(mappedBy = "filiere")
    private List<ClasseEntity> classes;

    @Override
    public String toString() {
        return "FiliereEntity{" +
                "libelle=" + libelle +

                '}';
    }
}
