package com.example.rag.application.command.producer

import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import java.util.concurrent.BlockingQueue

class EnrollQuestionProducer(
    private val queue: BlockingQueue<QnAEvent.EnrollQuestion>
) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.EnrollQuestion) {
            queue.put(event)
        }
    }
}