package com.example.rag.application.event.handler

import core.rag.event.QnAEvent

interface QnAEventHandler {
    fun handle(event: QnAEvent)
}
