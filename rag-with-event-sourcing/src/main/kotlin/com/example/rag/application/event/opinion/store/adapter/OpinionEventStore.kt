package com.example.rag.application.event.opinion.store.adapter

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "opinion_event_store")
class OpinionEventStore(
    @Column(name = "opinion_id", nullable = false)
    val opinionId: UUID,

    @Column(name = "event_type", nullable = false)
    val eventType: String,

    @Column(name = "question_id", nullable = false)
    val questionId: String,

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "content", nullable = false, length = 1000)
    val content: String,

    @Column(name = "user_id", nullable = false)
    val userId: String,

    @Column(name = "event_timestamp", nullable = false)
    val eventTimestamp: LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
