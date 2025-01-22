package com.example.rag.adapter.web.request

import com.example.rag.adapter.web.cleanHtml

data class QuestionUpdateRequest(
    val userId: String,
    val category: String,
    val title: String,
    val content: String
) {
    fun cleanData(): QuestionUpdateRequest =
        QuestionUpdateRequest(userId.cleanHtml(), title.cleanHtml(), category.cleanHtml(), content.cleanHtml())
}
