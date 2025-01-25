package com.example.rag.application.command.consumer

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

class UpdateQuestionConsumer(
    private val queue: BlockingQueue<QnAEvent.UpdateQuestion>,
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
            qna.updateQuestion(event)
            search.updateQuestion(event)
        }
    }
}