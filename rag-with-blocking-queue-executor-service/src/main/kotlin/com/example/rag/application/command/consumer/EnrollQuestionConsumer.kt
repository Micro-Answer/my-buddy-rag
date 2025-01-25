package com.example.rag.application.command.consumer

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

class EnrollQuestionConsumer(
    private val queue: BlockingQueue<QnAEvent.EnrollQuestion>,
    private val executorService: ExecutorService,
    private val nThreads: Int,
    private val qna: QnaSystem,
    private val search: SearchSystem,
    @Volatile private var running: Boolean = true
) {
    fun init() {
        repeat(nThreads) {
            executorService.submit {
                while(running) {
                    task()
                }
            }
        }
    }

    fun cleanup() {
        running = false
        queue.forEach { task() }
        executorService.shutdown()
    }

    fun task(): () -> Unit = {
        queue.poll(1, TimeUnit.SECONDS)?.let { event ->
            val questionId = qna.enrollQuestion(event).questionId
            search.enrollQuestion(questionId!!, event)
        }
    }
}