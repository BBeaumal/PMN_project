package com.backend.qcmplus.service;

import com.backend.qcmplus.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    List<Utilisateur> listAllUser();

    Utilisateur saveUser(Utilisateur user);

    Optional<Utilisateur> getUtilisateur(Long id);

    void deleteUtilisateur(Long id);

    Utilisateur getUtilisateurByLogin(String login);
}
