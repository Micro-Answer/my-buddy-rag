package com.example.rag.qna.adapter.output.opinion

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OpinionRepository: JpaRepository<OpinionEntity, Long> {
    @Query("SELECT o FROM OpinionEntity o WHERE o.questionId = :questionId ORDER BY o.createdAt DESC")
    fun findByQuestionId(questionId: String, pageable: Pageable): Page<OpinionEntity>
}