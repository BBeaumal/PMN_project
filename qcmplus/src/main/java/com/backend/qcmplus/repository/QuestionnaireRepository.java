package com.backend.qcmplus.repository;

import com.backend.qcmplus.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    void deleteById(Long id);

    Optional<Questionnaire> findById(Long id);
}
