package com.ism.core.web.controllers.impl;

import com.ism.core.data.entities.CoursEntity;
import com.ism.core.services.CoursService;
import com.ism.core.web.controllers.HomeController;
import com.ism.core.web.dto.response.CoursDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class HomeControllerImpl implements HomeController {
    private final CoursService coursService;
    @Override
    public String home(Model model,
                       @RequestParam(defaultValue = "0",name = "page") int page,
                       @RequestParam(defaultValue = "5",name = "size") int size,
                       @AuthenticationPrincipal UserDetails user) {
        if (user == null) {
            return "redirect:/login"; // Redirige vers la page 404
        }
        System.out.println("La methode existe");
        Pageable pageable = PageRequest.of(page, size);
        Page<CoursEntity> allCoursByDoneFalse = coursService.getAllCoursByDoneFalse(pageable);
        Stream<CoursDtoResponse> coursDtoResponseStream = allCoursByDoneFalse.stream().map(
                cours -> CoursDtoResponse.toDto(cours)
        );
        model.addAttribute("cours",coursDtoResponseStream);
        return "home/index";
    }

    @Override
    public String pageNotFound(Model model) {
        return "pageNotFound/index";
    }
}
