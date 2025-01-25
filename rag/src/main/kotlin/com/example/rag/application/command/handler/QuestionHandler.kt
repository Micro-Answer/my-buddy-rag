package com.example.rag.application.command.handler

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import core.search.SearchSystem

class QuestionHandler(private val qna: QnaSystem, private val search: SearchSystem, val task: (event: QnAEvent) -> Unit) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        task(event)
    }
}