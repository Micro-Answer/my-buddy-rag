package core.rag

import java.time.LocalDateTime

interface QuestionTitleDTO {
    fun questionId(): String
    fun title(): String
    fun userId(): String
    fun createdAt(): LocalDateTime
}
