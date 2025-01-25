package com.example.rag.application.message.publisher

import core.rag.event.QnAEvent
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class Publisher(private val rabbitTemplate: RabbitTemplate) {

    fun enrollQuestion(command: QnAEvent.EnrollQuestion) {
        rabbitTemplate.convertAndSend("topic.enrollQuestion", command)
    }

    fun updateQuestion(command: QnAEvent.UpdateQuestion) {
        rabbitTemplate.convertAndSend("topic.updateQuestion", command)
    }

    fun deleteQuestion(command: QnAEvent.DeleteQuestion) {
        rabbitTemplate.convertAndSend("topic.deleteQuestion", command)
    }

    fun enrollOpinion(command: QnAEvent.EnrollOpinion) {
        rabbitTemplate.convertAndSend("topic.enrollOpinion", command)
    }

    fun updateOpinion(command: QnAEvent.UpdateOpinion) {
        rabbitTemplate.convertAndSend("topic.updateOpinion", command)
    }

    fun deleteOpinion(command: QnAEvent.DeleteOpinion) {
        rabbitTemplate.convertAndSend("topic.deleteOpinion", command)
    }
}
