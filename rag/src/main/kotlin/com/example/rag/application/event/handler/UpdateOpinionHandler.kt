package com.example.rag.application.event.handler

import core.qna.QnaSystem
import core.rag.event.QnAEvent

class UpdateOpinionHandler(private val qna: QnaSystem) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.UpdateOpinion) {
            qna.updateOpinion(event)
        }
    }
}