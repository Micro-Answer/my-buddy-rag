package com.example.rag.application.event.listener

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class Listener(
    private val qna: QnaSystem,
    private val search: SearchSystem,
) {
    fun execute(event: QnAEvent) {
        when (event) {
            is QnAEvent.EnrollQuestion -> enrollQuestion(event)
            is QnAEvent.UpdateQuestion -> updateQuestion(event)
            is QnAEvent.DeleteQuestion -> deleteQuestion(event)
            is QnAEvent.EnrollOpinion -> enrollOpinion(event)
            is QnAEvent.UpdateOpinion -> updateOpinion(event)
            is QnAEvent.DeleteOpinion -> deleteOpinion(event)
            QnAEvent.Shutdown -> {}
        }
    }

    @EventListener
    private fun enrollQuestion(command: QnAEvent.EnrollQuestion) {
        qna.enrollQuestion(command).questionId?.let { questionId ->
            search.enrollQuestion(questionId, command)
        }
    }

    @EventListener
    private fun updateQuestion(command: QnAEvent.UpdateQuestion) {
        qna.updateQuestion(command)
        search.updateQuestion(command)
    }

    @EventListener
    private fun deleteQuestion(command: QnAEvent.DeleteQuestion) {
        qna.deleteQuestion(command)
        search.deleteQuestion(command)
    }

    @EventListener
    private fun enrollOpinion(command: QnAEvent.EnrollOpinion) {
        qna.enrollOpinion(command)
    }

    @EventListener
    private fun updateOpinion(command: QnAEvent.UpdateOpinion) {
        qna.updateOpinion(command)
    }

    @EventListener
    private fun deleteOpinion(command: QnAEvent.DeleteOpinion) {
        qna.deleteOpinion(command)
    }
}