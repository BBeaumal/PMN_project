package com.backend.qcmplus.service;

import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class UtilisateurService {

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

        public Utilisateur getUtilisateur(Integer id) {
            return userRepository.findById(id).get();
        }

        public void deleteUtilisateur(Integer id) {
            userRepository.deleteById(id);
        }
    }
}
