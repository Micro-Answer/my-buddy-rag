package com.example.rag.application.command.handler

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import core.search.SearchSystem

class EnrollQuestionHandler(private val qna: QnaSystem, private val search: SearchSystem) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.EnrollQuestion) {
            val questionId = qna.enrollQuestion(event).questionId
            search.enrollQuestion(questionId!!, event)
        }
    }
}