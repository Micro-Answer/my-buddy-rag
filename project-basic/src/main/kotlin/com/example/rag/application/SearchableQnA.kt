package com.example.rag.application

import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.search.SearchSystem
import org.springframework.stereotype.Component

@Component
class SearchableQnA(private val qna: QnaSystem, private val search: SearchSystem) {
    init {
        println("create SearchableQnA")
    }

    fun enrollQuestion(userId: String, title: String, category: String, content: String) {
        qna.enrollQuestion(userId, title, category, content).questionId.also { // 부수 효과를 고려하여 also
            search.enrollQuestion(it!!, title, category, content) // 부수 효과
        }
    }

    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String) {
        qna.updateQuestion(userId, questionId, title, category, content)
        search.updateQuestion(questionId, title, category, content)
    }

    fun deleteQuestion(userId: String, questionId: String) {
        qna.deleteQuestion(userId, questionId)
        search.deleteQuestion(questionId)
    }

    fun readQuestion(questionId: String): Question
        = qna.readQuestion(questionId)

    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle>
        = qna.readQuestionTitles(category, offset, limit)

    fun enrollOpinion(userId: String, questionId: String, title: String, content: String) {
        qna.enrollOpinion(userId, questionId, title, content)
    }

    fun updateOpinion(userId: String, opinionId: String, title: String, content: String) {
        qna.updateOpinion(userId, opinionId, title, content)
    }

    fun deleteOpinion(userId: String, opinionId: String) {
        qna.deleteOpinion(userId, opinionId)
    }

    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion>
        = qna.readOpinions(questionId, offset, limit)

    fun search(query: String): String
        = search.search(query).let {
            qna.readOpinions(it, 0, 1)[0].content
        }
}