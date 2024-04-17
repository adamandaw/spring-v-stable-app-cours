package com.ism.core.security.services.impl;

import com.ism.core.security.data.entities.RoleEntity;
import com.ism.core.security.data.entities.UserEntity;
import com.ism.core.security.data.repositories.RoleRepository;
import com.ism.core.security.data.repositories.UserRepository;
import com.ism.core.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService, UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public UserEntity getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity saveUser(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        if (user!=null) throw new RuntimeException("User already exist");
        user=new UserEntity(username,passwordEncoder.encode(password));
        //user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public RoleEntity saveRole(String roleName) {
        RoleEntity role = roleRepository.findByRoleName(roleName);
        if (role!=null) throw new RuntimeException("Role already exist");
        role = new RoleEntity(roleName,null);
        //role.setActive(true);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserEntity user = userRepository.findByUsername(username);
        if (user==null) throw new RuntimeException("User not found");
        RoleEntity role = roleRepository.findByRoleName(roleName);
        if (role==null) throw new RuntimeException("Role not found");
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public void removeRoleToUser(String username, String roleName) {
        // Recherche de l'utilisateur
        UserEntity user = userRepository.findByUsername(username);
        if (user==null) throw new RuntimeException("User not found");
        // Recherche de son role
        RoleEntity role = roleRepository.findByRoleName(roleName);
        if (role==null) throw new RuntimeException("Role not found");
        // suppression
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity appUser = userRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("User not found");

        //transformer appUser en UserDetails les roles, ils les appellent GrantyAuthority
        List<SimpleGrantedAuthority> authorities = appUser.getRoles()
                .stream()
                .map(appRole -> new SimpleGrantedAuthority(appRole.getRoleName())).toList();

        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                authorities
        );
    }
}
