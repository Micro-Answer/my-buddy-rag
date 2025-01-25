package com.example.rag.application.command.producer

import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import java.util.concurrent.BlockingQueue

class EnrollOpinionProducer(
    private val queue: BlockingQueue<QnAEvent.EnrollOpinion>
) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.EnrollOpinion) {
            queue.put(event)
        }
    }
}