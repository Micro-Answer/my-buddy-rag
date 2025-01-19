package com.example.rag.qna.adapter.output.opinion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<OpinionEntity, Long> {
    @Query("SELECT o FROM OpinionEntity o WHERE o.questionId = :questionId ORDER BY o.createdAt DESC")
    List<OpinionEntity> findByQuestionId(String questionId, int offset, int limit);
}