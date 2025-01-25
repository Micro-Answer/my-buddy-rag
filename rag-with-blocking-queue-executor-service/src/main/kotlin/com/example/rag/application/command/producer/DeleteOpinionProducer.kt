package com.example.rag.application.command.producer

import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import java.util.concurrent.BlockingQueue

class DeleteOpinionProducer(
    private val queue: BlockingQueue<QnAEvent.DeleteOpinion>
) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.DeleteOpinion) {
            queue.put(event)
        }
    }
}