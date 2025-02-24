package com.example.rag.application.event.opinion.store.adapter

import com.example.rag.application.event.opinion.store.port.ReadOnlyOpinionEvent
import com.example.rag.application.event.opinion.store.port.WriteOnlyOpinionEvent
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OpinionEventAdapter(private val repository: OpinionEventRepository) : ReadOnlyOpinionEvent, WriteOnlyOpinionEvent {
    override fun recordOpinionEvent(event: OpinionEventStore): Long {
        val savedEvent = repository.save(event)
        return savedEvent.id ?: throw IllegalStateException("Saved event ID should not be null")
    }

    override fun findByOpinionId(opinionId: UUID): List<OpinionEventStore>
        = repository.findByOpinionId(opinionId)

    override fun findById(id: Long): OpinionEventStore
        = repository.findById(id).orElseThrow()
}
