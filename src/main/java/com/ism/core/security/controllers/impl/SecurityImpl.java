package com.ism.core.security.controllers.impl;

import com.ism.core.security.controllers.Security;
import com.ism.core.security.data.entities.UserEntity;
import com.ism.core.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SecurityImpl implements Security {
    private final SecurityService securityService;
    @Override
    public String loadLoginPage() {
//
        return "auth/login";
    }

    @Override
    public String login(UserDetails user) {
        System.out.println(user.getUsername() + " est connecté !");
        // anyMatch dès qu'il trouve un critere valid …
        if(user.getAuthorities().stream().anyMatch(role -> role.getAuthority().compareTo("AC") == 0) ){
            log.info("role test ac ");
            return "redirect:/ac/setting";
        }
        if(user.getAuthorities().stream().anyMatch(role -> role.getAuthority().compareTo("RP") == 0) ){
            log.info("role test RP ");
            UserEntity usr = securityService.getUser(user.getUsername());
            return "redirect:/ac/setting/classe";
        }
        return "redirect:/login";
    }
}
