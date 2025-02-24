package com.example.rag.qna.adapter.output.question

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "questions")
class QuestionEntity(
    @Id
    val id: UUID,

    @Column(name = "category", nullable = false)
    var category: String,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false, length = 2000)
    var content: String,

    @Column(name = "user_id", nullable = false)
    var userId: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = createdAt
) {
    @Version
    var version = 0
}