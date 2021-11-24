package com.backend.qcmplus.controller;

import java.util.List;
import java.util.Optional;

import com.backend.qcmplus.model.Question;
import com.backend.qcmplus.model.Questionnaire;
import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.service.QuestionService;
import com.backend.qcmplus.service.QuestionnaireService;
import com.backend.qcmplus.service.UtilisateurService;
import com.backend.qcmplus.utils.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class QcmController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private QuestionnaireService surveyService;

    @Autowired
    private QuestionService questionService;

    // Find All User
    @GetMapping("/users")
    Mono<List<Utilisateur>> findAllUser() {
        return Mono.just(utilisateurService.listAllUser());
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

    // Save User
    // return 201 instead of 200
    // @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    Mono<Utilisateur> newUser(@RequestBody Utilisateur newUser) {
        return Mono.just(utilisateurService.saveUser(newUser));
    }

    // Save Survey
    // return 201 instead of 200
    // @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/survey")
    Mono<Questionnaire> newSurvey(@RequestBody Questionnaire newSurvey) {
        return Mono.just(surveyService.saveSurvey(newSurvey));
    }

    // Save Question
    // return 201 instead of 200
    // @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/question")
    Mono<Question> newQuestion(@RequestBody Question newQuestion) {
        return Mono.just(questionService.saveQuestion(newQuestion));
    }

    // Find User
    @GetMapping("/users/{id}")
    Mono<Utilisateur> findOneUser(@PathVariable Long id) throws UserNotFoundException {
        Optional<Utilisateur> foundUser = utilisateurService.findById(id);
        if (foundUser.isEmpty())
            throw new UserNotFoundException(id);
        return Mono.just(foundUser.get());
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

    // Save or update User
    @PutMapping("/users/{id}")
    Mono<Utilisateur> saveOrUpdateUser(@RequestBody Utilisateur newUser, @PathVariable Long id) {
        Optional<Utilisateur> foundUser = utilisateurService.findById(id);

        return foundUser.map(x -> {
            x.setLogin(newUser.getLogin());
            x.setMail(newUser.getMail());
            x.setNom(newUser.getNom());
            x.setPassword(newUser.getPassword());
            x.setPrenom(newUser.getPrenom());
            x.setSociete(newUser.getSociete());

            return Mono.just(utilisateurService.saveUser(x));
        }).orElseGet(() -> {
            newUser.setIdUtilisateur(id);
            return Mono.just(utilisateurService.saveUser(newUser));
        });
    }

    // Save or update Survey
    @PutMapping("/survey/{id}")
    Mono<Questionnaire> saveOrUpdateSurvey(@RequestBody Questionnaire newSurvey, @PathVariable Long id) {
        Optional<Questionnaire> foundSurvey = surveyService.findById(id);

        return foundSurvey.map(x -> {
            x.setAuteur(newSurvey.getAuteur());
            x.setDateCreation(newSurvey.getDateCreation());
            x.setNomQuestionnaire(newSurvey.getNomQuestionnaire());
            x.setListeQuestion(newSurvey.getListeQuestion());

            return Mono.just(surveyService.saveSurvey(x));
        }).orElseGet(() -> {
            newSurvey.setIdQuestionnaire(id);
            return Mono.just(surveyService.saveSurvey(newSurvey));
        });
    }

    // Save or update Question
    @PutMapping("/question/{id}")
    Mono<Question> saveOrUpdateQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {
        Optional<Question> foundQuestion = questionService.findById(id);

        return foundQuestion.map(x -> {
            x.setIntitule(newQuestion.getIntitule());
            x.setQuestionnaire(newQuestion.getQuestionnaire());
            x.setResultat(newQuestion.getResultat());

            return Mono.just(questionService.saveQuestion(x));
        }).orElseGet(() -> {
            newQuestion.setIdQuestion(id);
            return Mono.just(questionService.saveQuestion(newQuestion));
        });
    }

    // update societe only
    // @PatchMapping("/users/{id}")
    /*
     * Utilisateur patch(@RequestBody Map<String, String> update, @PathVariable Long
     * id) {
     * 
     * Optional<Utilisateur> foundUser = Optional.of(repository.findById(id));
     * return foundUser.map(x -> {
     * 
     * String societe = update.get("societe"); if (!StringUtils.hasLength(societe))
     * { x.setSociete(societe);
     * 
     * // better create a custom method to update a value = :newValue where id = :id
     * return repository.save(x); } else { //throw new
     * UserUnSupportedFieldPatchException(update.keySet()); }
     * 
     * }).orElseGet(() -> { throw new UserNotFoundException(id); });
     * 
     * }
     */

    @DeleteMapping("/users/{id}")
    void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }

    @DeleteMapping("/survey/{id}")
    void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }

    @DeleteMapping("/question/{id}")
    void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

}