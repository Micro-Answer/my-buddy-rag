package com.example.rag.application.handler

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler

class DeleteOpinionHandler(private val qna: QnaSystem) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.DeleteOpinion) {
            qna.deleteOpinion(event)
        }
    }
}