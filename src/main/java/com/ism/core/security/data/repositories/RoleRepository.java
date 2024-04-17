package com.ism.core.security.data.repositories;

import com.ism.core.security.data.entities.RoleEntity;
import com.ism.core.security.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRoleName(String roleName);
}
