package com.example.rag.application.event.opinion.publisher

import com.example.rag.application.event.opinion.store.adapter.OpinionEventStore
import com.example.rag.application.event.opinion.store.port.WriteOnlyOpinionEvent
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class OpinionEventPublisher(
    private val writeEvent: WriteOnlyOpinionEvent,
    private val rabbitTemplate: RabbitTemplate
) {
    fun writeEvent(event: OpinionEvent) {
        val id = UUID.randomUUID()
        val eventStore = OpinionEventStore(
            id,
            event.eventType,
            event.questionId,
            event.title,
            event.content,
            event.userId
        )
        writeEvent.recordOpinionEvent(eventStore)
    }
}
