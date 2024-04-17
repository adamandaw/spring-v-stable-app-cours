package com.ism.core.web.controllers;

import com.ism.core.data.entities.CoursEntity;
import com.ism.core.web.dto.request.CoursDtoRequest;
import com.ism.core.web.dto.request.SessionCoursDtoRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface SessionCoursController {

@GetMapping("cours/{id}")
    String showPageSessionCour(@PathVariable Long id,
                               Model model,@RequestParam(defaultValue = "0",name = "page") int page,
                               @RequestParam(defaultValue = "5",name = "size") int size);

//    @GetMapping("module/{moduleId}")
//    String filterByModule(Model model, @PathVariable(value = "moduleId",required = false) Long moduleId);
    @GetMapping("session/{idCours}/new")
    String showFormSessionCour(  @PathVariable Long idCours,Model model,RedirectAttributes redirectAttributes);

    @GetMapping("session/{id}")
    String validateSessionDeCour(@PathVariable Long id,RedirectAttributes redirectAttributes);

    @GetMapping("session/{id}/canceled")
    String cancelSessionDeCour(@PathVariable Long id,RedirectAttributes redirectAttributes);

    @PostMapping("session/{id}/new")
    String save(@PathVariable Long id,
               @Valid SessionCoursDtoRequest sessionCoursDtoRequest,
                Model model, @RequestParam("typeSession") String typeSession,
                @RequestParam("participations") Long[] participations, BindingResult bindingResult,
                RedirectAttributes redirectAttributes);



}
