package com.backend.qcmplus.service;

import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository userRepository;

    public List<Utilisateur> listAllUser() {
        return userRepository.findAll();
    }

    public void saveUser(Utilisateur user) {
        userRepository.save(user);
    }

    public Optional<Utilisateur> getUtilisateur(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUtilisateur(Long id) {
        userRepository.deleteById(id);
    }
}
