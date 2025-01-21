package com.example.rag.qna.adapter.output.question

import com.example.rag.qna.domain.Question

interface QuestionPersistencePort {
    fun saveQuestion(domain: Question): Question
    fun updateQuestion(domain: Question)
    fun deleteQuestion(questionId: String)
    fun findQuestionById(questionId: String): Question
    fun findQuestionsByUserId(userId: String, offset: Int, limit: Int): List<Question>
}