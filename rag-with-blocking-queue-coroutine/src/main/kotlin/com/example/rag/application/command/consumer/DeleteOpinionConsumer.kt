package com.example.rag.application.command.consumer

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import kotlinx.coroutines.*
import java.util.concurrent.BlockingQueue

class DeleteOpinionConsumer(
    private val queue: BlockingQueue<QnAEvent.DeleteOpinion>,
    private val qna: QnaSystem,
    private val applicationScope: CoroutineScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, exception ->
            println("Caught exception: ${exception.message}")
        })
) {
    fun init() {
        applicationScope.launch {
            while (isActive) {
                val event = queue.take()
                qna.deleteOpinion(event)
            }
        }
    }

    fun cleanup() {
        applicationScope.cancel()
    }
}