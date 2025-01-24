package com.example.rag.adapter.web.request

import com.example.rag.adapter.web.cleanHtml
import core.rag.event.QnAEvent
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class OpinionRequest(
    @field:NotNull(message = "User ID cannot be null")
    @field:Size(min = 5, max = 100, message = "User ID size must be between 5 and 100 characters")
    val userId: String,

    @field:NotNull(message = "Question ID cannot be null")
    @field:Size(min = 1, max = 1_000_000, message = "Question ID size must be between 1 and 1_000_000 characters")
    val questionId: String,

    @field:NotNull(message = "Title cannot be null")
    @field:Size(min = 5, max = 100, message = "Title size must be between 5 and 100 characters")
    val title: String,

    @field:NotNull(message = "Content cannot be null")
    @field:Size(min = 10, max = 5000, message = "Content size must be between 10 and 5000 characters")
    val content: String
) {
    fun cleanData(): OpinionRequest =
        OpinionRequest(userId.cleanHtml(), questionId.cleanHtml(), title.cleanHtml(), content.cleanHtml())
}
fun OpinionRequest.toEvent() =
    QnAEvent.EnrollOpinion(questionId, title, content, userId)