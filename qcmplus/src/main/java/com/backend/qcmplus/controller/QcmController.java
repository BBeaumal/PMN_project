package com.backend.qcmplus.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.repository.UtilisateurRepository;
import com.backend.qcmplus.utils.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
//import org.springframework.util.StringUtils;

@RestController
public class QcmController {

    @Autowired
    private UtilisateurRepository repository;

    // Find
    @GetMapping("/users")
    List<Utilisateur> findAll() {
        return repository.findAll();
    }

    // Save
    // return 201 instead of 200
    // @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    Utilisateur newBook(@RequestBody Utilisateur newUser) {
        return repository.save(newUser);
    }

    // Find
    @GetMapping("/users/{id}")
    Utilisateur findOne(@PathVariable Long id) throws UserNotFoundException {
        Optional<Utilisateur> foundUser = Optional.of(repository.findById(id));
        if (!foundUser.isPresent())
            throw new UserNotFoundException(id);
        return foundUser.get();
    }

    // Save or update
    @PutMapping("/users/{id}")
    Utilisateur saveOrUpdate(@RequestBody Utilisateur newUser, @PathVariable Long id) {
        Optional<Utilisateur> foundUser = Optional.of(repository.findById(id));

        return foundUser.map(x -> {
            x.setLogin(newUser.getLogin());
            x.setMail(newUser.getMail());
            x.setNom(newUser.getNom());
            x.setPassword(newUser.getPassword());
            x.setPrenom(newUser.getPrenom());
            x.setSociete(newUser.getSociete());

            return repository.save(x);
        }).orElseGet(() -> {
            newUser.setIdUtilisateur(id);
            return repository.save(newUser);
        });
    }

    // update societe only
    @PatchMapping("/users/{id}")
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
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}