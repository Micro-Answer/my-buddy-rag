package com.example.rag.application.command.consumer

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import kotlinx.coroutines.*
import java.util.concurrent.BlockingQueue

class UpdateQuestionConsumer(
    private val queue: BlockingQueue<QnAEvent.UpdateQuestion>,
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
                qna.updateQuestion(event)
                search.updateQuestion(event)
            }
        }
    }

    fun cleanup() {
        applicationScope.cancel()
    }
}