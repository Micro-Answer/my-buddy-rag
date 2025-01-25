package com.example.rag.application.event.publisher

import com.example.rag.application.event.events.*
import core.rag.event.QnAEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class Publisher(private val publisher: ApplicationEventPublisher) {
    fun enrollQuestion(command: QnAEvent.EnrollQuestion) {
        val event = QuestionEnrollEvent(this, command)
        publisher.publishEvent(event)
    }

    fun updateQuestion(command: QnAEvent.UpdateQuestion) {
        val event = QuestionUpdateEvent(this, command)
        publisher.publishEvent(event)
    }

    fun deleteQuestion(command: QnAEvent.DeleteQuestion) {
        val event = QuestionDeleteEvent(this, command)
        publisher.publishEvent(event)
    }

    fun enrollOpinion(command: QnAEvent.EnrollOpinion) {
        val event = OpinionEnrollEvent(this, command)
        publisher.publishEvent(event)
    }

    fun updateOpinion(command: QnAEvent.UpdateOpinion) {
        val event = OpinionUpdateEvent(this, command)
        publisher.publishEvent(event)
    }

    fun deleteOpinion(command: QnAEvent.DeleteOpinion) {
        val event = OpinionDeleteEvent(this, command)
        publisher.publishEvent(event)
    }
}
