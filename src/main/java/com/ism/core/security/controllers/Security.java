package com.ism.core.security.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface Security {
    @GetMapping("/login")
    String loadLoginPage();
    @PostMapping("/login")
    String login(@AuthenticationPrincipal UserDetails user);

}
