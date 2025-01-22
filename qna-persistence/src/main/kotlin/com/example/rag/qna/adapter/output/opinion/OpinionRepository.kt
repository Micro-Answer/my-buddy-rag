package com.example.rag.qna.adapter.output.opinion

import com.example.rag.qna.adapter.output.opinion.OpinionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OpinionRepository: JpaRepository<OpinionEntity, Long> {
    @Query("SELECT o FROM OpinionEntity o WHERE o.questionId = :questionId ORDER BY o.createdAt DESC")
    fun findByQuestionId(questionId: String, offset: Int, limit: Int): List<OpinionEntity>
}