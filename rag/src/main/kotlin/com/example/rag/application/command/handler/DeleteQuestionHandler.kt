package com.example.rag.application.command.handler

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler

class DeleteQuestionHandler(private val qna: QnaSystem) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.DeleteQuestion) {
            qna.deleteQuestion(event)
        }
    }
}