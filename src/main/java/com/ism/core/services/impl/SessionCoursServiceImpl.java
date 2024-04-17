package com.ism.core.services.impl;

import com.ism.core.data.entities.*;
import com.ism.core.data.repositories.*;
import com.ism.core.services.SessionCoursService;
import com.ism.core.web.dto.request.SessionCoursDtoRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionCoursServiceImpl implements SessionCoursService {

    private final SessionCoursRepository sessionRepository;
    private final ParticipationCoursRepository participationCoursRepository;
    private final ClasseRepository classeRepository;
    private final CoursRepository coursRepository;
    private final  InscriptionRepository inscriptionRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;
//    private final SallesRepository sallesRepository;

    @Override
    public boolean isExistByThisDate(LocalDate date, SessionDeCoursEntity sessionDeCours) {
        List<SessionDeCoursEntity> liste = sessionRepository.findAllByDateAndIsCompletedFalseAndIsArchivedFalse(sessionDeCours.getDate());
        liste.stream().anyMatch(
                uneSession -> sessionDeCours.getDebutHeure().equals(uneSession.getDebutHeure()));
        return true;
    }
    @Override
    public boolean verifyByProfessorAvailable( SessionDeCoursEntity sessionDeCours) {
        if (isExistByThisDate(sessionDeCours.getDate(),sessionDeCours)){
            List<SessionDeCoursEntity> liste = sessionRepository
                    .findAllByDateAndIsCompletedFalseAndIsArchivedFalse(sessionDeCours.getDate());
            if (liste.size() > 0) {
                boolean professorOccupee = liste.stream()
                        .anyMatch(uneSession ->
                                uneSession.getCour().getProfesseur().equals(sessionDeCours.getCour().getProfesseur())
                                        && !uneSession.getCour().equals(sessionDeCours.getCour())
                        || uneSession.getCour().equals(sessionDeCours.getCour()));
                System.out.println("\nprof indiponible FONCTION NATIF\n");
                return professorOccupee;
            }
        }
        System.out.println("\nprof EST DIFFERENT \n");
        return false;
    }
    @Override
    public  Optional<SessionDeCoursEntity> getSessionDeCoursById(Long id) {

        return sessionRepository.findById(id);
    }

    @Override
    public List<SessionDeCoursEntity> getSessionByCour(Long id) {
        List<SessionDeCoursEntity> sessionDeCours =
                sessionRepository.findByCour_IdAndAndIsArchivedIsFalseAndIsCompletedIsFalse(id);
        if (!sessionDeCours.isEmpty()) {
            return sessionDeCours.stream().toList();
        } else {
            return new ArrayList<>(); // ou une autre valeur par défaut appropriée
        }
    }

    @Override
    public int setSessionToCompleted(Long id) {
        sessionRepository.majIsCompleted(id);
        return 1;
    }

    @Override
    public int archiveSession(Long id) {
        return sessionRepository.majIsArchived(id)  > 0 ? 1: 0;
    }

    @Override
    public boolean verifyBySalleAvailable(LocalDate date, SessionCoursDtoRequest sessionDeCours) {
        if (sessionDeCours.getTypeSession().equals("En Présentiel")) {
            SalleEntity currentSalle = sessionDeCours.getSalle();
            List<SessionDeCoursEntity> liste = sessionRepository.findAllByDateAndIsCompletedFalseAndIsArchivedFalse(sessionDeCours.getDate());

            if (liste.size() > 0) {
                boolean salleOccupee = liste.stream().anyMatch(chaqueSalle ->
                        chaqueSalle.getSalle() != null && chaqueSalle.getSalle().equals(currentSalle)
                                && chaqueSalle.getDebutHeure().equals(sessionDeCours.getDebutHeure())
                );

                return salleOccupee;
            } else {
                return false;
            }
        }

        return false;
    }



    @Override
    public boolean verifyByHourAvailable(SessionDeCoursEntity sessionDeCours) {

        return sessionDeCours.getCour().getNbrHeureEffectuer()
                + sessionDeCours.getDureeHeure()
                <=
                sessionDeCours.getCour().getNbrHeureGlobal();
    }


    @Override
    public SessionDeCoursEntity push(@PathVariable Long id, SessionCoursDtoRequest sessionCoursDtoRequest,
                      @RequestParam("typeSession") String typeSession,
                      @RequestParam("participations") Long[] participations) {

        //recherche le cours si exist ?
        SessionDeCoursEntity session=sessionCoursDtoRequest.toEntity();
        Optional<CoursEntity> courById = coursRepository.findById(id);
        if (courById != null){
            CoursEntity coursEntity = courById.get();
            //conversion => Obj
            session.setTypeSession(typeSession);
            session.setIsCompleted(false);
            session.setIsArchived(false);
            session.setCour(courById.orElseThrow());
            // Calcul de la différence d'heure
            Duration duration = Duration.between(session.getDebutHeure(),session.getFinHeure());
            int duree = Math.toIntExact(duration.toHours());

            session.setDureeHeure(duree);
            if (session.getTypeSession().equals("En ligne")) {
                session.setSalle(null);
            }else {
                session.setSalle(sessionCoursDtoRequest.getSalle());
            }

            System.out.println("\n\n********************//////////  ZONE DEBUG METHOD PUSH SERVICE IMPL //////**************************************\n\n");
//            System.out.println(session.getCour().getNbrHeureGlobal());
//            System.out.println(session.getDureeHeure());
            if (verifyByHourAvailable(session) && verifyByProfessorAvailable(session)==false){
                sessionRepository.save(session);
                //modification heure planifier
                int value= coursEntity.getNbrHeurePlanifier() == 0 ? duree : coursEntity.getNbrHeurePlanifier()+duree;
                coursRepository.updateNbrHeurePlanifier(coursEntity.getId(),value);
                //Classe de relation Participation
                int nbrEtudiant=0;
                AnneeScolaireEntity anneeScolaireEnCour= anneeScolaireRepository.findByIsActiveIsTrue();
                for (Long idClasse : participations) {
                    ClasseEntity classe = classeRepository.findById(idClasse).orElseThrow();
                    participationCoursRepository.saveAndFlush(new ParticipationCoursEntity(session,classe)) ;
                    List<InscriptionEntity> inscriptions = inscriptionRepository.findByAnneeScolaireAndClasse_Id(anneeScolaireEnCour, classe.getId());
                    for (int i = 0; i < inscriptions.size(); i++) {
                        InscriptionEntity inscription = inscriptions.get(i);
                        nbrEtudiant = i+1;
                    }
                    // System.out.println("\n\n\n\nIndex  Nombre etudiant : "+ nbrEtudiant);
//                String s = verifyBySalleCapacity(nbrEtudiant, sessionCoursDtoRequest) ? "\n\nSalle peut accepter" : "\n\nplace petit";
                }
            }else {

                session=null;
            }

        }
        return session;
    }

    @Override
    public boolean verifyBySalleCapacity(int nbrEtudiant, SessionCoursDtoRequest sessionDeCours) {
        for (int j = 1; j <= sessionDeCours.getParticipations().size(); j++) {
            nbrEtudiant = j;
            // Autres opérations à effectuer dans la boucle
        }
        Long nbrPlace = sessionDeCours.getSalle().getNbrPlace();
        System.out.println("\nNOMBRE ETUDIANT = "+nbrEtudiant);
        return nbrEtudiant <= nbrPlace;
    }

    @Override
    public Page<SessionDeCoursEntity> getAllSessionByCour(CoursEntity cours, Pageable pageable) {
        return sessionRepository.findAllByCourAndIsCompletedFalseAndIsArchivedFalse(cours,pageable);
    }


}
//System.out.println(sessionCoursDtoRequest);
//            System.out.println("********************////////// OBJ //////**************************************\n\n");
//            System.out.println(session);
//            //System.out.println(session.getParticipationCours());
//            System.out.println("\n\n\"**********************************************************\n\n");