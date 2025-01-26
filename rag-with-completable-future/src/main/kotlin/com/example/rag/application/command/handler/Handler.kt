package com.example.rag.application.command.handler

import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler

class Handler(val task: (event: QnAEvent) -> Unit) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        task(event)
    }
}

