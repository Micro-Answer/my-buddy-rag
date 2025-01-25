package com.example.rag.application.message.subscriber

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class Listener(
    private val qna: QnaSystem,
    private val search: SearchSystem
) {

    @RabbitListener(queues = ["topic.enrollQuestion"])
    fun enrollQuestion(command: QnAEvent.EnrollQuestion) {
        qna.enrollQuestion(command).questionId?.let { questionId ->
            search.enrollQuestion(questionId, command)
        }
    }

    @RabbitListener(queues = ["topic.updateQuestion"])
    fun updateQuestion(command: QnAEvent.UpdateQuestion) {
        qna.updateQuestion(command)
        search.updateQuestion(command)
    }

    @RabbitListener(queues = ["topic.deleteQuestion"])
    fun deleteQuestion(command: QnAEvent.DeleteQuestion) {
        qna.deleteQuestion(command)
        search.deleteQuestion(command)
    }

    @RabbitListener(queues = ["topic.enrollOpinion"])
    fun enrollOpinion(command: QnAEvent.EnrollOpinion) {
        qna.enrollOpinion(command)
    }

    @RabbitListener(queues = ["topic.updateOpinion"])
    fun updateOpinion(command: QnAEvent.UpdateOpinion) {
        qna.updateOpinion(command)
    }

    @RabbitListener(queues = ["topic.deleteOpinion"])
    fun deleteOpinion(command: QnAEvent.DeleteOpinion) {
        qna.deleteOpinion(command)
    }
}