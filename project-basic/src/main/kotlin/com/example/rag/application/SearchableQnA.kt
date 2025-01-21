package com.example.rag.application

import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitleDTO
import core.search.SearchSystem
import org.springframework.stereotype.Component

@Component
class SearchableQnA(private val qna: QnaSystem, private val search: SearchSystem) {
    init {
        println("create SearchableQnA")
    }

    fun enrollQuestion(userId: String, title: String, category: String, content: String): String {
        val questionId = qna.enrollQuestion(userId, title, category, content)
        search.enrollQuestion(questionId, title, category, content)
        return "success"
    }

    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String): String {
        qna.updateQuestion(userId, questionId, title, category, content)
        search.updateQuestion(questionId, title, category, content)
        return "success"
    }

    fun deleteQuestion(userId: String, questionId: String): String {
        qna.deleteQuestion(userId, questionId)
        search.deleteQuestion(questionId)
        return "success"
    }

    fun readQuestion(questionId: String): Question {
        return qna.readQuestion(questionId)
    }

    fun readQuestionTitles(category: String, offset: Int, limit: Int): Array<QuestionTitleDTO> {
        return qna.readQuestionTitles(category, offset, limit)
    }

    fun enrollOpinion(userId: String, questionId: String, title: String, content: String): String {
        qna.enrollOpinion(userId, questionId, title, content)
        return "success"
    }

    fun updateOpinion(userId: String, opinionId: String, title: String, content: String): String {
        qna.updateOpinion(userId, opinionId, title, content)
        return "success"
    }

    fun deleteOpinion(userId: String, opinionId: String): String {
        qna.deleteOpinion(userId, opinionId)
        return "success"
    }

    fun readOpinions(questionId: String, offset: Int, limit: Int): Array<Opinion> {
        return qna.readOpinions(questionId, offset, limit)
    }

    fun search(query: String): String {
        val questionId = search.search(query)
        return qna.readOpinions(questionId, 1, 1)[0].getContent()
    }
}