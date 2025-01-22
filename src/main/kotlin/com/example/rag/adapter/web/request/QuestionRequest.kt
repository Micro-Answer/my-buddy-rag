package com.example.rag.adapter.web.request

import com.example.rag.adapter.web.cleanHtml
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class QuestionRequest(
    @field:NotNull(message = "User ID cannot be null")
    @field:Size(min = 5, max = 100, message = "User ID must be between 5 and 100 characters")
    val userId: String,

    @field:NotNull(message = "Title cannot be null")
    @field:Size(min = 5, max = 100, message = "Title size must be between 5 and 100 characters")
    val title: String,

    @field:NotNull(message = "Category cannot be null")
    @field:Size(min = 3, max = 50, message = "Category size must be between 3 and 50 characters")
    val category: String,

    @field:NotNull(message = "Content cannot be null")
    @field:Size(min = 10, max = 5000, message = "Content size must be between 10 and 5000 characters")
    val content: String
) {
    fun cleanData(): QuestionRequest =
        QuestionRequest(userId.cleanHtml(), title.cleanHtml(), category.cleanHtml(), content.cleanHtml())
}
