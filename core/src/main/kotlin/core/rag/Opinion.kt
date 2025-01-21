package core.rag

import java.time.LocalDateTime

data class Opinion (
    val questionId: String,
    val title: String,
    val content: String,
    val userId: String,
    val opinionId: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)

