package com.ism.core.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "classe_module")
public class ClasseModuleEntity extends  AbstractEntity{

    @ManyToOne
    private ClasseEntity classe;
    @ManyToOne
    private ModuleEntity module;

    @Override
    public String toString() {
        return "ClasseModuleEntity{" +

                "} " + super.toString();
    }


}
