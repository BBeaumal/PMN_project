package com.backend.qcmplus.controller;

import com.backend.qcmplus.model.Question;
import com.backend.qcmplus.model.Questionnaire;
import com.backend.qcmplus.model.ReponseUtilisateurQuestion;
import com.backend.qcmplus.service.QuestionService;
import com.backend.qcmplus.service.QuestionnaireService;
import com.backend.qcmplus.service.ReponseUtilisateurQuestionService;
import com.backend.qcmplus.service.UtilisateurService;
import com.backend.qcmplus.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private QuestionnaireService surveyService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReponseUtilisateurQuestionService reponseUtilisateurQuestionService;

    @GetMapping("/login")
    Mono<String> hello() {
        return Mono.just("loged");
    }

    // Find All Survey
    @GetMapping("/survey")
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

    @GetMapping("/questionnaire/{idQuestionnaire}/parcours")
    public double noteOneQuestionnaire(@PathVariable Long idQuestionnaire) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ReponseUtilisateurQuestion> listeQuestionsRepondues = reponseUtilisateurQuestionService.listAllReponsesFromLastTentative(idQuestionnaire);
        double note = 0;

        if (principal instanceof UserDetails) {
            for (ReponseUtilisateurQuestion reponseUtilisateurQuestion : listeQuestionsRepondues) {
                if (reponseUtilisateurQuestion.getReponse() == 1) {
                    note++;
                }
            }
            note=(note / listeQuestionsRepondues.size()) * 20;
            return note;
        }
        return 0;
    }

/*    Mono<List<ReponseUtilisateurQuestion>> findAllReponses(@PathVariable Long id) throws UserNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            System.out.println("Utilisateur : "+username);
            Utilisateur tempUser = utilisateurService.getUtilisateurByLogin(username);
            if ( tempUser == null )
                System.out.println("User not found with id survey");
            else{
                if (tempUser.isIsadmin())
                    System.out.println("User must not be Admin");
                else{
                    System.out.println("Utilisateur : "+tempUser.getIdUtilisateur()+ "  : "+tempUser.getPrenom());
                    return Mono.just(reponseUtilisateurQuestionService.listAllQuestionsByUtilisateur(tempUser.getIdUtilisateur()));
                }
            }
        } else {
          throw new UserNotFoundException(id);
        }
        return Mono.just(reponseUtilisateurQuestionService.listAllQuestionsByUtilisateur(id));
    }*/

}
