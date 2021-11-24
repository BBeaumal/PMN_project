package com.backend.qcmplus.repository;

import com.backend.qcmplus.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    void deleteById(Long id);

    Utilisateur findById(Long id);
}
