package com.ism.core.web.controllers.impl;

import com.ism.core.data.entities.ClasseEntity;
import com.ism.core.services.ClasseService;
import com.ism.core.web.controllers.SettingController;
import com.ism.core.web.dto.response.ClasseDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class SettingControllerImpl implements SettingController {
    private  final ClasseService classeService;
    @Override
    public String showPageSetting() {
        return "settings/index";
    }

    @Override
    public String showPageSettingClasse() {
        return "settings/classe/index";
    }

    @Override
    public String settingClasseNotActive(Model model,
                                         @RequestParam(defaultValue = "0",name = "page") int page,
                                         @RequestParam(defaultValue = "5",name = "size") int size){


        Pageable pageable = PageRequest.of(page, size);
        Page<ClasseEntity> allClasseNotActive = classeService.getAllClasseNotActive(pageable);

        Stream<ClasseDtoResponse> classeNotActiveDtoResponse = allClasseNotActive.stream().map(
                classeEntity -> ClasseDtoResponse.toDto(classeEntity)
        );
        model.addAttribute("classes",classeNotActiveDtoResponse);
        return "settings/classe/list";
    }

    @Override
    public String settingActiveClasse(Long id) {
        Optional<ClasseEntity> classe = classeService.getClasse(id);
        if (classe!=null){
            System.out.println("ok");
            return "redirect:/";
        }

        return "redirect:/home";
    }


    @Override
    public String showPageSettingInscription() {
        return "settings/inscription/index";
    }
}
