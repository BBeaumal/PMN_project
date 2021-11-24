package com.backend.qcmplus.service;

import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    List<Utilisateur> listAllUser();

    Utilisateur saveUser(Utilisateur user);

    Optional<Utilisateur> getUtilisateur(Long id);

    void deleteUtilisateur(Long id);
}
