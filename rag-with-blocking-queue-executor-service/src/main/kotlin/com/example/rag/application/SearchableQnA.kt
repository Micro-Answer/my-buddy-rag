package com.example.rag.application

import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.search.SearchSystem
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

class SearchableQnA(
    private val qna: QnaSystem,
    private val search: SearchSystem,
    private val executorService: ExecutorService,
    private val nThreads: Int
) {
    private val questionEnrollQueue = ArrayBlockingQueue<Question> (1000)
    private val questionUpdateQueue = ArrayBlockingQueue<Question> (1000)
    private val opinionEnrollQueue = ArrayBlockingQueue<Opinion> (1000)
    private val opinionUpdateQueue = ArrayBlockingQueue<Opinion> (1000)
    @Volatile private var running = true

    fun init() {
        runExecutorService { enrollQuestion() }
        runExecutorService { updateQuestion() }
        runExecutorService { enrollOpinion() }
        runExecutorService { updateOpinion() }
    }

    private fun runExecutorService(task: () -> Unit) {
        repeat(nThreads) {
            executorService.submit {
                while(running) {
                    task()
                }
            }
        }
    }

    private fun enrollQuestion(): () -> Unit =
        {
            questionEnrollQueue.poll(1, TimeUnit.SECONDS)?.let { question ->
                val questionId = qna.enrollQuestion(question.userId, question.title, question.category, question.content).questionId
                search.enrollQuestion(questionId!!, question.title, question.category, question.content)
            }
        }

    private fun updateQuestion(): () -> Unit =
        {
            questionUpdateQueue.poll(1, TimeUnit.SECONDS)?.let { question ->
                qna.updateQuestion(question.userId, question.questionId!!, question.title, question.category, question.content)
                search.updateQuestion(question.questionId!!, question.title, question.category, question.content)
            }
        }

    private fun enrollOpinion(): () -> Unit =
        {
            opinionEnrollQueue.poll(1, TimeUnit.SECONDS)?.let { opinion ->
                qna.enrollOpinion(opinion.userId, opinion.questionId, opinion.title, opinion.content)
            }
        }

    private fun updateOpinion(): () -> Unit =
        {
            opinionUpdateQueue.poll(1, TimeUnit.SECONDS)?.let { opinion ->
                qna.updateOpinion(opinion.userId, opinion.questionId, opinion.title, opinion.content)
            }
        }

    private fun cleanup() {
        running = false
        questionEnrollQueue.forEach { enrollQuestion() }
        questionUpdateQueue.forEach { updateQuestion() }
        opinionEnrollQueue.forEach { enrollOpinion() }
        opinionUpdateQueue.forEach { updateOpinion() }
        executorService.shutdown()
    }

    fun enrollQuestion(userId: String, title: String, category: String, content: String) {
        questionEnrollQueue.put(Question(category, title, content, userId))
    }

    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String) {
        questionUpdateQueue.put(Question(category, title, content, userId, questionId))
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
        opinionEnrollQueue.put(Opinion(questionId, title, content, userId))
    }

    fun updateOpinion(userId: String, opinionId: String, title: String, content: String) {
        opinionUpdateQueue.put(Opinion("", title, content, userId, opinionId))
    }

    fun deleteOpinion(userId: String, opinionId: String) {
        runAsync { qna.deleteOpinion(userId, opinionId) }
    }

    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        qna.readOpinions(questionId, offset, limit)

    fun search(query: String): String {
        val questionId = search.search(query)
        return qna.readOpinions(questionId, 0, 1).getOrNull(0)?.content ?: "No Content"
    }

    private fun runAsync(task: () -> Unit): Unit {
        try {
            CompletableFuture.runAsync(task)
        } catch (e: Exception) {
            println("Error in async operation: ${e.message}")
        }
    }
}
