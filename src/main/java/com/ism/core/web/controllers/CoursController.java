package com.ism.core.web.controllers;

import com.ism.core.web.dto.request.CoursDtoRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface CoursController {


    @GetMapping("cours/new")
    String showFormCour(Model model);


    @PostMapping("/save/cours")
    String save(Model model, CoursDtoRequest coursDtoRequest, BindingResult bindingResult,
                RedirectAttributes redirectAttributes);
}
