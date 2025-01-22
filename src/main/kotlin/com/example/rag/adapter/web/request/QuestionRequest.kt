package com.example.rag.adapter.web.request

import com.example.rag.adapter.web.cleanHtml

data class QuestionRequest(
    val userId: String,
    val title: String,
    val category: String,
    val content: String
) {
    fun cleanData(): QuestionRequest =
        QuestionRequest(userId.cleanHtml(), title.cleanHtml(), category.cleanHtml(), content.cleanHtml())
}
