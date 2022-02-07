package com.backend.qcmplus.service.impl;

import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.repository.UtilisateurRepository;
import com.backend.qcmplus.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    UtilisateurRepository userRepository;

    /**
     *
     * @return all user who are not admin
     */
    public List<Utilisateur> listAllUser() {
        return userRepository.findAllByIsadminFalse();
    }

    /**
     * save a user
     * @param user is a Utilisateur object we want to save
     * @return saved user
     */
    public Utilisateur saveUser(Utilisateur user) {
        return userRepository.save(user);
    }

    /**
     * retrieve user by id
     * @param id of User
     * @return Optional User from database
     */
    public Optional<Utilisateur> getUtilisateur(Long id) {
        return userRepository.findByidUtilisateur(id);
    }

    /**
     * Delete user by id
     * @param id user
     */
    public void deleteUtilisateur(Long id) {
        userRepository.deleteByidUtilisateur(id);
    }

    /**
     * retrieve user by login
     * @param login of the user
     * @return user
     */
    @Override
    public Utilisateur getUtilisateurByLogin(String login) {
        return userRepository.findUtilisateurByLogin(login);
    }

    /**
     *
     * @return the current user who is logged in
     */
    public Utilisateur getCurrentUtilisateur() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            throw new UsernameNotFoundException("username not found");
        }
        return getUtilisateurByLogin(username);
    }
}
