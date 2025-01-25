package com.example.rag.application.command.producer

import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import java.util.concurrent.BlockingQueue

class UpdateOpinionProducer(
    private val queue: BlockingQueue<QnAEvent.UpdateOpinion>
) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.UpdateOpinion) {
            queue.put(event)
        }
    }
}