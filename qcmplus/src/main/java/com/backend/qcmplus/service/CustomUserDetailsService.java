package com.backend.qcmplus.service;

import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = repository.findUtilisateurByLogin(username);
        CustomUserDetails userDetails = null;
        if (utilisateur != null) {
            userDetails = new CustomUserDetails();
            userDetails.setUtilisateur(utilisateur);
        } else {
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }
        return userDetails;

    }

}
