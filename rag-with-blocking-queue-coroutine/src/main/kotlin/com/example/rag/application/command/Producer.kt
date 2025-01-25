package com.example.rag.application.command

import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import java.util.concurrent.BlockingQueue

class Producer<T> (
    private val queue: BlockingQueue<T>,
    private val task: (event: QnAEvent) -> Unit
) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        task(event)
    }
}