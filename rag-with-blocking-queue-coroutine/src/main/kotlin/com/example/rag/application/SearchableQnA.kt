package com.example.rag.application

import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.search.SearchSystem
import kotlinx.coroutines.*
import java.util.concurrent.ArrayBlockingQueue

class SearchableQnA(private val qna: QnaSystem, private val search: SearchSystem) {
    private val questionEnrollQueue = ArrayBlockingQueue<Question> (1000)
    private val questionUpdateQueue = ArrayBlockingQueue<Question> (1000)
    private val opinionEnrollQueue = ArrayBlockingQueue<Opinion> (1000)
    private val opinionUpdateQueue = ArrayBlockingQueue<Opinion> (1000)
    private val applicationScope: CoroutineScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, exception ->
            println("Caught exception: ${exception.message}")
        })

    fun init() {
        applicationScope.launch {
            while (isActive) { enrollQuestion() }
        }
        applicationScope.launch {
            while (isActive) { updateQuestion() }
        }
        applicationScope.launch {
            while (isActive) { enrollOpinion() }
        }
        applicationScope.launch {
            while (isActive) { updateOpinion() }
        }
    }

    private fun enrollQuestion(): () -> Unit =
        {
            val question = questionEnrollQueue.take()
            val questionId = qna.enrollQuestion(question.userId, question.title, question.category, question.content).questionId
            search.enrollQuestion(questionId!!, question.title, question.category, question.content)
        }

    private fun updateQuestion(): () -> Unit =
        {
            val question = questionUpdateQueue.take()
            qna.updateQuestion(question.userId, question.questionId!!, question.title, question.category, question.content)
            search.updateQuestion(question.questionId!!, question.title, question.category, question.content)
        }

    private fun enrollOpinion(): () -> Unit =
        {
            val opinion = opinionEnrollQueue.take()
            qna.enrollOpinion(opinion.userId, opinion.questionId, opinion.title, opinion.content)
        }

    private fun updateOpinion(): () -> Unit =
        {
            val opinion = opinionUpdateQueue.take()
            qna.updateOpinion(opinion.userId, opinion.questionId, opinion.title, opinion.content)
        }

    private fun cleanup() {
        applicationScope.cancel()
    }

    fun enrollQuestion(userId: String, title: String, category: String, content: String) {
        questionEnrollQueue.put(Question(category, title, content, userId))
    }

    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String) {
        questionUpdateQueue.put(Question(category, title, content, userId, questionId))
    }

    fun deleteQuestion(userId: String, questionId: String) {
        launch { qna.deleteQuestion(userId, questionId) }
        launch { search.deleteQuestion(questionId) }
    }

    fun readQuestion(questionId: String): Question =
        qna.readQuestion(questionId)


    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> =
        qna.readQuestionTitles(category, offset, limit)

    fun enrollOpinion(userId: String, questionId: String, title: String, content: String) {
        opinionEnrollQueue.put(Opinion(questionId, title, content, userId))
    }

    fun updateOpinion(userId: String, opinionId: String, title: String, content: String) {
        opinionUpdateQueue.put(Opinion("", title, content, userId, opinionId))
    }

    fun deleteOpinion(userId: String, opinionId: String) {
        launch { qna.deleteOpinion(userId, opinionId) }
    }

    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        qna.readOpinions(questionId, offset, limit)

    fun search(query: String): String {
        val questionId = search.search(query)
        return qna.readOpinions(questionId, 0, 1).getOrNull(0)?.content ?: "No Content"
    }

    private fun launch(task: () -> Unit) {
        applicationScope.launch { task() }
    }
}