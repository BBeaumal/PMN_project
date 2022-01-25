package com.backend.qcmplus.controller;

import com.backend.qcmplus.model.Question;
import com.backend.qcmplus.model.Questionnaire;
import com.backend.qcmplus.model.ReponseUtilisateurQuestion;
import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.service.QuestionService;
import com.backend.qcmplus.service.QuestionnaireService;
import com.backend.qcmplus.service.ReponseUtilisateurQuestionService;
import com.backend.qcmplus.service.UtilisateurService;
import com.backend.qcmplus.utils.NotFoundException;
import com.backend.qcmplus.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    private QuestionnaireService surveyService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ReponseUtilisateurQuestionService reponseUtilisateurQuestionService;

    @GetMapping("/login")
    Mono<String> hello() {
        return Mono.just("loged");
    }

    // Find All Survey
    @GetMapping("/surveys")
    Mono<List<Questionnaire>> findAllSurvey() {
        return Mono.just(surveyService.listAllSurvey());
    }

    // Find All Question
    @GetMapping("/question")
    Mono<List<Question>> findAllQuestion() {
        return Mono.just(questionService.listAllQuestion());
    }

    // Find Survey
    @GetMapping("/survey/{id}")
    Mono<Questionnaire> findOneSurvey(@PathVariable Long id) throws UserNotFoundException {
        Optional<Questionnaire> foundSurvey = surveyService.findById(id);
        if (foundSurvey.isEmpty())
            throw new UserNotFoundException(id);
        return Mono.just(foundSurvey.get());
    }

    // Find Question
    @GetMapping("/question/{id}")
    Mono<Question> findOneQuestion(@PathVariable Long id) throws UserNotFoundException {
        Optional<Question> foundQuestion = questionService.findById(id);
        if (foundQuestion.isEmpty())
            throw new UserNotFoundException(id);
        return Mono.just(foundQuestion.get());
    }

    @GetMapping("/survey/{id}/reponses")
    Mono<List<ReponseUtilisateurQuestion>> findAllReponse(@PathVariable Long id) throws NotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            System.out.println("Utilisateur : "+username);
            Utilisateur tempUser = utilisateurService.getUtilisateurByLogin(username);
            if ( tempUser == null )
                throw new NotFoundException("User not found with id survey", 0L);
            else{
                if (tempUser.isIsadmin())
                    throw new NotFoundException("User must not be Admin", 0L);
                else{
                    System.out.println("Utilisateur : "+tempUser.getIdUtilisateur()+ "  : "+tempUser.getPrenom());
                    return Mono.just(reponseUtilisateurQuestionService.listAllQuestionByIdUtilisateur(tempUser.getIdUtilisateur()));
                }
            }
        } else {
            throw new NotFoundException("User not found logged in", 0L);
        }
    }
}