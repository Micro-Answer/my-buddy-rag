package core.rag

import java.time.LocalDateTime

data class Opinion (
    val opinionId: String? = null,
    val questionId: String,
    val title: String,
    val content: String,
    val userId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

