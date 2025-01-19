package com.example.rag.qna.adapter.output.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query("SELECT q FROM QuestionEntity q WHERE q.userId = :userId ORDER BY q.createdAt DESC")
    List<QuestionEntity> findByUserId(String userId, int offset, int limit);
}
