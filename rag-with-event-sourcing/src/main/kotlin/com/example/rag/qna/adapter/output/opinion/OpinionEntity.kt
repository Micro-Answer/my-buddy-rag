package com.example.rag.qna.adapter.output.opinion

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "opinions")
class OpinionEntity(
    @Id
    val id: UUID,

    @Column(name = "question_id", nullable = false)
    var questionId: String,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false, length = 1000)
    var content: String,

    @Column(name = "user_id", nullable = false)
    var userId: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = createdAt
) {
    @Version
    var version = 0 // 낙관적 락
}