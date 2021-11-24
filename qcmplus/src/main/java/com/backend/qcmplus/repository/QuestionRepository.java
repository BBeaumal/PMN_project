package com.backend.qcmplus.repository;

import com.backend.qcmplus.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    void deleteById(Long id);

    Optional<Question> findById(Long id);
}
