package com.example.rag.application.command.consumer

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import kotlinx.coroutines.*
import java.util.concurrent.BlockingQueue

class EnrollQuestionConsumer(
    private val queue: BlockingQueue<QnAEvent.EnrollQuestion>,
    private val qna: QnaSystem,
    private val search: SearchSystem,
    private val applicationScope: CoroutineScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, exception ->
            println("Caught exception: ${exception.message}")
        })
) {
    fun init() {
        applicationScope.launch {
            while (isActive) {
                val event = queue.take()
                val questionId = qna.enrollQuestion(event).questionId
                search.enrollQuestion(questionId!!, event)
            }
        }
    }

    fun cleanup() {
        applicationScope.cancel()
    }
}