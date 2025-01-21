package core.rag

import java.time.LocalDateTime

data class Question(
    val category: String,
    val title: String,
    val content: String,
    val userId: String,
    val questionId: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)