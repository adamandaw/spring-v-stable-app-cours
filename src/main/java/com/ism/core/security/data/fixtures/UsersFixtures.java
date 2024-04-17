package com.ism.core.security.data.fixtures;

import com.ism.core.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
@Order(2)
public class UsersFixtures implements CommandLineRunner {
    private final SecurityService securityService;
    @Override
    public void run(String... args) throws Exception {
        securityService.saveUser("adama","passer");
        securityService.addRoleToUser("adama","RP");
        securityService.addRoleToUser("adama","AC");
    }
}
