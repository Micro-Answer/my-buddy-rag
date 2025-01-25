package com.example.rag.application.command.producer

import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import java.util.concurrent.BlockingQueue

class DeleteQuestionProducer(
    private val queue: BlockingQueue<QnAEvent.DeleteQuestion>
) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.DeleteQuestion) {
            queue.put(event)
        }
    }
}