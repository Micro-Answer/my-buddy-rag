package com.example.rag.application.command.consumer

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import kotlinx.coroutines.*
import java.util.concurrent.BlockingQueue

class UpdateOpinionConsumer(
    private val queue: BlockingQueue<QnAEvent.UpdateOpinion>,
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
                qna.updateOpinion(event)
            }
        }
    }

    fun cleanup() {
        applicationScope.cancel()
    }
}