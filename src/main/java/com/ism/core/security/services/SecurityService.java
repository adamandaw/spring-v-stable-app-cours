package com.ism.core.security.services;

import com.ism.core.security.data.entities.RoleEntity;
import com.ism.core.security.data.entities.UserEntity;

public interface SecurityService {
    UserEntity getUser(String username);

    UserEntity saveUser(String username, String password);
    RoleEntity saveRole(String roleName);
    void addRoleToUser(String username,String roleName);
    void removeRoleToUser(String username,String roleName);

}
