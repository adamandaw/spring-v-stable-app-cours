package com.ism.core.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface SettingController {

@GetMapping("ac/setting")
    String showPageSetting();

    @GetMapping("ac/setting/classe")
    String showPageSettingClasse();

    @GetMapping("/ac/setting/classe/no-active")
    String settingClasseNotActive(
            Model model,
            @RequestParam(defaultValue = "0",name = "page") int page,
            @RequestParam(defaultValue = "5",name = "size") int size
            );
    @GetMapping("ac/setting/classe/{id}/active")
    String settingActiveClasse(
            @PathVariable(name = "id") Long id
    );


    @GetMapping("ac/setting/inscription")
    String showPageSettingInscription();

}
