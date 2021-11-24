package com.backend.qcmplus.controller;

import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.service.UtilisateurService;
import com.backend.qcmplus.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/rest")
public class AdminController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Save
    // return 201 instead of 200
    // @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    Mono<Utilisateur> newBook(@RequestBody Utilisateur newUser) {
        String pwd = newUser.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        newUser.setPassword(encryptPwd);
        return Mono.just(utilisateurService.saveUser(newUser));
    }

    // Find
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users")
    Mono<List<Utilisateur>> findAll() {
        return Mono.just(utilisateurService.listAllUser());
    }

    // Find
    @GetMapping("/users/{id}")
    Mono<Utilisateur> findOne(@PathVariable Long id) throws UserNotFoundException {
        Optional<Utilisateur> foundUser = utilisateurService.getUtilisateur(id);
        if (foundUser.isEmpty())
            throw new UserNotFoundException(id);
        return Mono.just(foundUser.get());
    }

    // Save or update
    @PutMapping("/users/{id}")
    Mono<Utilisateur> saveOrUpdate(@RequestBody Utilisateur newUser, @PathVariable Long id) {
        Optional<Utilisateur> foundUser = utilisateurService.getUtilisateur(id);

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
        utilisateurService.deleteUtilisateur(id);
    }
}
