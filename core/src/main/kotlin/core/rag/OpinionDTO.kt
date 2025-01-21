package core.rag

import java.time.LocalDateTime

interface OpinionDTO {
    fun getOpinionId(): String?
    fun getQuestionId(): String?
    fun getTitle(): String?
    fun getContent(): String?
    fun getUserId(): String?
    fun getCreatedAt(): LocalDateTime?
    fun getUpdatedAt(): LocalDateTime?
}

