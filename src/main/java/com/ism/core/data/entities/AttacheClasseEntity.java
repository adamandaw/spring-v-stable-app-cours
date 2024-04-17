package com.ism.core.data.entities;

import com.ism.core.data.enums.EGrade;
import com.ism.core.data.enums.ESpecialite;
import com.ism.core.security.data.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@DiscriminatorValue(value = "AttacheDeClasse")
public class AttacheClasseEntity extends UserEntity {

}
