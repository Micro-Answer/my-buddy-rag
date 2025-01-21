package core.rag

import java.time.LocalDateTime

interface QuestionDTO {
    fun getQuestionId(): String?
    fun getCategory(): String
    fun getTitle(): String
    fun getContent(): String
    fun getUserId(): String
    fun getCreatedAt(): LocalDateTime?
    fun getUpdatedAt(): LocalDateTime?
}