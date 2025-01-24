package com.example.rag.application

import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.search.SearchSystem
import kotlinx.coroutines.*

class SearchableQnA(private val qna: QnaSystem, private val search: SearchSystem) {
    private val applicationScope: CoroutineScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, exception ->
            println("Caught exception: ${exception.message}")
        })

    fun enrollQuestion(userId: String, title: String, category: String, content: String) {
        launch {
            qna.enrollQuestion(userId, title, category, content)
                .questionId?.let { search.enrollQuestion(it, title, category, content) }
        }
    }

    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String) {
        launch { qna.updateQuestion(userId, questionId, title, category, content) }
        launch { search.updateQuestion(questionId, title, category, content) }
    }

    fun deleteQuestion(userId: String, questionId: String) {
        launch { qna.deleteQuestion(userId, questionId) }
        launch { search.deleteQuestion(questionId) }
    }

    suspend fun readQuestion(questionId: String): Question =
        withCurrentContext { qna.readQuestion(questionId) }


    suspend fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> =
        withCurrentContext { qna.readQuestionTitles(category, offset, limit) }

    fun enrollOpinion(userId: String, questionId: String, title: String, content: String) {
        launch { qna.enrollOpinion(userId, questionId, title, content) }
    }

    fun updateOpinion(userId: String, opinionId: String, title: String, content: String) {
        launch { qna.updateOpinion(userId, opinionId, title, content) }
    }

    fun deleteOpinion(userId: String, opinionId: String) {
        launch { qna.deleteOpinion(userId, opinionId) }
    }

    suspend fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        withCurrentContext { qna.readOpinions(questionId, offset, limit) }

    suspend fun search(query: String): String {
        val questionId = withCurrentContext { search.search(query) }
        return qna.readOpinions(questionId, 0, 1).getOrNull(0)?.content ?: "No Content"
    }

    private suspend fun <T> withCurrentContext(task: suspend () -> T): T =
        try {
            withContext(Dispatchers.IO) { task() }
        } catch (e: Exception) {
            println("Error during withContext operation: ${e.message}")
            throw e
        }

    private fun launch(task: () -> Unit) {
        applicationScope.launch { task() }
    }
}