package com.ism.core.data.entities;

import com.ism.core.security.data.entities.UserEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@DiscriminatorValue(value = "ResponsablePedagogique")
public class ResponsablePedagogiqueEntity extends UserEntity {

}
