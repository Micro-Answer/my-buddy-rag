package com.example.rag.qna.adapter.output.opinion

import com.example.rag.qna.domain.Opinion

interface OpinionPersistencePort {
    fun saveOpinion(domain: Opinion): OpinionEntity
    fun updateOpinion(domain: Opinion)
    fun deleteOpinion(opinionId: String) // MongoDB 식별자의 문자열 타입 고려
    fun findOpinionById(opinionId: String): Opinion // MongoDB 식별자의 문자열 타입 고려
    fun findOpinionsByQuestionId(questionId: String, offset: Int, limit: Int): List<Opinion>
}