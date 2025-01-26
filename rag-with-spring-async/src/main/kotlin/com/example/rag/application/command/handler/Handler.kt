package com.example.rag.application.command.handler

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import core.search.SearchSystem
import org.springframework.stereotype.Component

@Component
class Handler(
    private val qna: QnaSystem,
    private val search: SearchSystem,
) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        when (event) {
            is QnAEvent.EnrollQuestion -> {
                qna.enrollQuestion(event).questionId?.let { questionId ->
                    search.enrollQuestion(questionId, event)
                }
            }
            is QnAEvent.UpdateQuestion -> {
                qna.updateQuestion(event)
                search.updateQuestion(event)
            }
            is QnAEvent.DeleteQuestion -> {
                qna.deleteQuestion(event)
                search.deleteQuestion(event)
            }
            is QnAEvent.EnrollOpinion -> {
                qna.enrollOpinion(event)
            }
            is QnAEvent.UpdateOpinion -> {
                qna.updateOpinion(event)
            }
            is QnAEvent.DeleteOpinion -> {
                qna.deleteOpinion(event)
            }
            QnAEvent.Shutdown -> {}
        }
    }
}