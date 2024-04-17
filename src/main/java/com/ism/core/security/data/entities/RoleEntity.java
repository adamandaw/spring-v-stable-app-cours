package com.ism.core.security.data.entities;

import com.ism.core.data.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "roles")
@EqualsAndHashCode(callSuper=false)
public class RoleEntity extends AbstractEntity {

    @Column(unique = true,nullable = true)
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles") //ici on laisse le fetch ease : on ne veut pas au moment ou il charge les roles qu'il charge les users
    List<UserEntity> users;

    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }
}
