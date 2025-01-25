package com.example.rag.application.command.producer

import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import java.util.concurrent.BlockingQueue

class UpdateQuestionProducer(
    private val queue: BlockingQueue<QnAEvent.UpdateQuestion>
) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.UpdateQuestion) {
            queue.put(event)
        }
    }
}