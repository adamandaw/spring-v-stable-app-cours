package com.ism.core.web.controllers.impl;

import com.ism.core.data.entities.ModuleEntity;
import com.ism.core.data.entities.ProfesseurEntity;
import com.ism.core.data.entities.SemestreEntity;
import com.ism.core.data.repositories.SemestresRepository;
import com.ism.core.services.CoursService;
import com.ism.core.services.ProfesseurService;
import com.ism.core.web.controllers.CoursController;
import com.ism.core.web.dto.request.CoursDtoRequest;
import com.ism.core.web.dto.response.ModuleDtoResponse;
import com.ism.core.web.dto.response.ProfesseurSimpleDtoResponse;
import com.ism.core.web.dto.response.SemetreDtoResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.commons.data.entities.ContactEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class CoursControllerImpl implements CoursController {
    private  final CoursService coursService;
    private final ProfesseurService professeurService;





    @Override
    public String showFormCour(Model model) {
        CoursDtoRequest coursDtoRequest= CoursDtoRequest.builder().build();
        List<SemestreEntity> semestres = coursService.getAllSemestres();
        Stream<SemetreDtoResponse> semestreDto = semestres.stream().map(
                semestre -> SemetreDtoResponse.toDto(semestre)
        );

        List<ModuleEntity> modules = coursService.getAllModules();
        Stream<ModuleDtoResponse> modulesDto = modules.stream().map(
                moduleEntity -> ModuleDtoResponse.toDto(moduleEntity)
        );

        List<ProfesseurEntity> allProfesseur = professeurService.getAllProfesseur();
        Stream<ProfesseurSimpleDtoResponse> professeursDto = allProfesseur.stream().map(
                professeur -> ProfesseurSimpleDtoResponse.toDto(professeur)
        );
        model.addAttribute("modules",modulesDto);
        model.addAttribute("semestres",semestreDto);
        model.addAttribute("professeurs",professeursDto);
        model.addAttribute("cours",coursDtoRequest);
        return "cours/form-add";
    }

    @Override
    public String save(Model model, @Valid CoursDtoRequest coursDtoRequest, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        String mode = "success";
        if (bindingResult.hasErrors()){
            Map<String,String> errors=new HashMap<>();
            List<FieldError> champErreur = bindingResult.getFieldErrors();
            champErreur.forEach(
                    errorName -> errors.put(errorName.getField(),errorName.getDefaultMessage())
            );
            redirectAttributes.addFlashAttribute("mode","error");
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:/cours/new";
        }else {
            coursService.store(coursDtoRequest);
            return "redirect:/";
        }


    }
}
