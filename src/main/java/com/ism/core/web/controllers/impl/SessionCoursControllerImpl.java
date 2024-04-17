package com.ism.core.web.controllers.impl;

import com.ism.core.data.entities.*;
import com.ism.core.data.enums.ETypeSession;
import com.ism.core.services.*;
import com.ism.core.web.controllers.SessionCoursController;
import com.ism.core.web.dto.request.ParticipationDtoRequest;
import com.ism.core.web.dto.request.SessionCoursDtoRequest;
import com.ism.core.web.dto.response.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
//@SessionAttributes({"currentCour"})
public class SessionCoursControllerImpl implements SessionCoursController {
    private  final CoursService coursService;
    private  final ClasseService classeService;
    private  final SalleService salleService;
    private final SessionCoursService sessionCoursService;
    private final IModuleService moduleService;
    @Override
    //ajopute filtrer par module
    public String showPageSessionCour(Long id, Model model,@RequestParam(defaultValue = "0",name = "page") int page,
                                      @RequestParam(defaultValue = "5",name = "size") int size) {
      CoursEntity currentCour = coursService.getCourById(id);
        if (currentCour == null){
            return "redirect:/page/404";
        }
        if (currentCour.getNbrHeureGlobal().equals(currentCour.getNbrHeureEffectuer())) {
            coursService.setCoursToIsDone(currentCour.getId());
            return "redirect:/";
        }
        CoursDtoResponse courDto = CoursDtoResponse.toDto(currentCour);
        List<ModuleDtoResponse> listModuleDto = moduleService.getAll().stream().map(
                moduleEntity -> ModuleDtoResponse.toDto(moduleEntity)
        ).toList();
        Pageable pageable= PageRequest.of(page, size);
        Page<SessionDeCoursEntity> allSessionByCour = sessionCoursService.getAllSessionByCour(currentCour, pageable);
        Stream<SessionCoursDtoResponse> sessionCoursDtoResponseStream = allSessionByCour.stream().map(
                sessionDeCours -> SessionCoursDtoResponse.toDto(sessionDeCours)
        );
        model.addAttribute("courIntegrale",courDto);
        model.addAttribute("modules",listModuleDto);
        model.addAttribute("ListeDeSessions",sessionCoursDtoResponseStream);
        //System.out.println(courDto);
        return "session/index";
    }

//    @Override
//    public String filterByModule(Model model, Long moduleId) {
//        System.out.println(moduleId);
//        CoursEntity courByModule = coursService.getByModule(moduleService.getById(moduleId));
//        if (courByModule != null){
//            return "redirect:/cours/"+courByModule.getId();
//        }
//        return "redirect:/";
//    }


    @Override
    public String showFormSessionCour(Long idCours, Model model,RedirectAttributes redirectAttributes) {
        CoursEntity courById = coursService.getCourById(idCours);
        if (courById != null){
            if (courById.getNbrHeureEffectuer().equals(courById.getNbrHeureGlobal())) {
                coursService.setCoursToIsDone(courById.getId());

                 redirectAttributes.addFlashAttribute("msg", "Impossible de planifier");
                return "redirect:/cours/"+idCours;
            }
            SessionCoursDtoRequest sessionCoursDtoRequest=SessionCoursDtoRequest.builder().build();
            // rechercher les classes par niveau
            SemestreEntity semestre = courById.getSemestre();
            List<ClasseEntity> classesByNiveau = classeService.getClassesByNiveau(semestre.getNiveauSemestre());
            Stream<ClasseDtoResponse> classeDtoResponseStream = classesByNiveau.stream().map(
                    cl -> {
                        return ClasseDtoResponse.toDto(cl);
                    }
            );
            //recupere le tytpe de session et salles de cour
            List<SalleEntity> allSalles = salleService.getAllSalles();

            Stream<SalleDtoResponse> salleDtoResponseStream = allSalles.stream().map(
                    salleEntity -> SalleDtoResponse.toDto(salleEntity)
            );



            model.addAttribute("types", ETypeSession.values());
            model.addAttribute("id", idCours);
            model.addAttribute("classes",classeDtoResponseStream);
            model.addAttribute("salles",salleDtoResponseStream);
            model.addAttribute("sessionObj",sessionCoursDtoRequest);
            //model.addAttribute("classe", ParticipationDtoRequest.builder().build());
            return "session/form-add-session";
        }
        return "redirect:/page/404";
    }

    @Override
    public String validateSessionDeCour(Long id,RedirectAttributes redirectAttributes) {
        Optional<SessionDeCoursEntity> sessionDeCours=sessionCoursService.getSessionDeCoursById(id);

        CoursEntity cour = sessionDeCours.get().getCour();

        //maj
        int value =cour.getNbrHeureEffectuer()==0 ? sessionDeCours.get().getDureeHeure() :
                sessionDeCours.get().getDureeHeure() +cour.getNbrHeureEffectuer(); // ancienne valeur
        coursService.updateHeureEffectuer(cour.getId(),value);
        sessionCoursService.setSessionToCompleted(sessionDeCours.get().getId());
        int data= cour.getNbrHeurePlanifier()<= 0 ?  0  :  Math.abs(sessionDeCours.get().getDureeHeure() - cour.getNbrHeurePlanifier()) ;
        coursService.updateHeurePlanifiorByCour(cour.getId(),data);
        if (cour.getNbrHeureGlobal().equals(cour.getNbrHeureEffectuer())) {
            coursService.setCoursToIsDone(sessionDeCours.get().getCour().getId());
        }
        redirectAttributes.addFlashAttribute("crudSuccess", "Mise à jour réussi.");
        return "redirect:/cours/"+cour.getId();
    }

    @Override
    public String cancelSessionDeCour(Long id,RedirectAttributes redirectAttributes) {
        Optional<SessionDeCoursEntity> sessionDeCours=sessionCoursService.getSessionDeCoursById(id);

        CoursEntity cour = sessionDeCours.get().getCour();
        int data =  Math.abs(cour.getNbrHeurePlanifier() - sessionDeCours.get().getDureeHeure());
        coursService.updateHeurePlanifiorByCour(cour.getId(),data);
        sessionCoursService.archiveSession(sessionDeCours.get().getId());
        redirectAttributes.addFlashAttribute("crudSuccess", "Mise à jour réussi.");
        return "redirect:/cours/"+cour.getId();
    }

    @Override

    public String save(@PathVariable Long id,
                       @Valid  SessionCoursDtoRequest sessionCoursDtoRequest, Model model,
                       @RequestParam("typeSession") String typeSession,
                       @RequestParam("participations") Long[] participations,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

//
//        CoursEntity courById = coursService.getCourById(id);
//        System.out.println(courById);
        if (bindingResult.hasErrors()){
            Map<String,String> errors=new HashMap<>();
            List<FieldError> champErreur = bindingResult.getFieldErrors();
            champErreur.forEach(
                    errorName -> errors.put(errorName.getField(),errorName.getDefaultMessage())
            );
            redirectAttributes.addFlashAttribute("mode","error");
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:/session/"+id+"/new";
        }

        if (sessionCoursService.verifyBySalleAvailable(sessionCoursDtoRequest.getDate(), sessionCoursDtoRequest)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            redirectAttributes.addFlashAttribute("errorDePlanification",
                    "La salle que vous avez choisie est indisponible le " + sessionCoursDtoRequest.getDate().format(formatter));
            return "redirect:/session/" + id + "/new";
        }
        // PROFESSEUR DISPO ?
//JE ME SUIS STOP ICI A REVOIR PAS BON
        if (  sessionCoursService.push(id, sessionCoursDtoRequest, typeSession, participations) != null
        || sessionCoursService.verifyByProfessorAvailable(
                sessionCoursService.push(id, sessionCoursDtoRequest, typeSession, participations))) {

        redirectAttributes.addFlashAttribute("profIndisponible",
                "Le professeur n'est pas disponible.");
            System.out.println("\n\n********************//////////  RENTRER DANS LA METHODE CONTROLLER IMPL //////**************************************\n\n");

            return "redirect:/session/"+id+"/new";
        }

        //System.out.println(sessionCoursService.push(id, sessionCoursDtoRequest, typeSession, participations));
       if ( sessionCoursService.push(id, sessionCoursDtoRequest, typeSession, participations) == null){
           System.out.println("null NULL");
           redirectAttributes.addFlashAttribute("heureDepasse", "La durée d'heure ne doit pas depassé le nombre d'heure globale.");
           return "redirect:/session/"+id+"/new";
       }
       // SCENARIO NOMINAL
        System.out.println("\n\n********************//////////  EN TRAIN DE PUSH //////**************************************\n\n");

        redirectAttributes.addFlashAttribute("crudSuccess", "Mise à jour réussi.");
        return "redirect:/cours/"+id;


    }
}

//            System.out.println("\n\n********************////////// ZONE DEBUG  //////**************************************\n\n");
//            System.out.println("Possible");
//            System.out.println("********************////////// ZONE DEBUG  //////**************************************\n\n");

//            boolean existByThisDate = sessionCoursService.isExistByThisDate(sessionCoursDtoRequest.getDate(), sessionCoursDtoRequest.toEntity());
//            String response = existByThisDate ? "on peut pas planifier a cette heure " : "on peut  planifier";
//              System.out.println(response);
//            System.out.println("********************////////// ZONE DEBUG  //////**************************************\n\n");
////            System.out.println(sessionCoursDtoRequest.getTypeSession());
//            System.out.println("\n\n********************////////// ZONE DEBUG  //////**************************************\n\n");
//            System.out.println("Impossible");