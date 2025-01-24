package com.example.rag.qna.adapter.output.opinion

import core.rag.Opinion

interface OpinionPersistencePort {
    fun getUserIdByOpinionId(opinionId: String): String?
    fun saveOpinion(domain: Opinion)
    fun updateOpinion(domain: Opinion)
    fun deleteOpinionById(opinionId: String) // MongoDB 식별자의 문자열 타입 고려
    fun findOpinionById(opinionId: String): Opinion // MongoDB 식별자의 문자열 타입 고려
    fun findOpinionsByQuestionId(questionId: String, offset: Int, limit: Int): List<Opinion>
}