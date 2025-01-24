package com.example.rag.application

import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.search.SearchSystem
import java.util.concurrent.CompletableFuture

class SearchableQnA(private val qna: QnaSystem, private val search: SearchSystem) {
    fun enrollQuestion(userId: String, title: String, category: String, content: String) {
        runAsync {
            qna.enrollQuestion(userId, title, category, content)
                .questionId?.let { search.enrollQuestion(it, title, category, content) }
        }
    }

    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String) {
        runAsync { qna.updateQuestion(userId, questionId, title, category, content) }
        runAsync { search.updateQuestion(questionId, title, category, content) }
    }

    fun deleteQuestion(userId: String, questionId: String) {
        runAsync { qna.deleteQuestion(userId, questionId) }
        runAsync { search.deleteQuestion(questionId) }
    }

    fun readQuestion(questionId: String): Question =
        qna.readQuestion(questionId)

    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> =
        qna.readQuestionTitles(category, offset, limit)

    fun enrollOpinion(userId: String, questionId: String, title: String, content: String) {
        runAsync { qna.enrollOpinion(userId, questionId, title, content) }
    }

    fun updateOpinion(userId: String, opinionId: String, title: String, content: String) {
        runAsync { qna.updateOpinion(userId, opinionId, title, content) }
    }

    fun deleteOpinion(userId: String, opinionId: String) {
        runAsync { qna.deleteOpinion(userId, opinionId) }
    }

    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        qna.readOpinions(questionId, offset, limit)

    fun search(query: String): String =
        search.search(query).let { questionId ->
            qna.readOpinions(questionId, 0, 1).getOrNull(0)?.content ?: "No Content"
        }

    private fun runAsync(task: () -> Unit): Unit {
        try {
            CompletableFuture.runAsync(task)
        } catch (e: Exception) {
            println("Error in async operation: ${e.message}")
        }
    }
}