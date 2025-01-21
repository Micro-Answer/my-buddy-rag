package core.rag

import java.time.LocalDateTime

data class QuestionTitle(val questionId: String, val title: String, val userId: String, val createdAt: LocalDateTime)
