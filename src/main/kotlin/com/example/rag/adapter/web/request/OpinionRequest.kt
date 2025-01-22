package com.example.rag.adapter.web.request

import com.example.rag.adapter.web.cleanHtml

data class OpinionRequest(
    val userId: String,
    val questionId: String,
    val title: String,
    val content: String
) {
    fun cleanData(): OpinionRequest =
        OpinionRequest(userId.cleanHtml(), questionId.cleanHtml(), title.cleanHtml(), content.cleanHtml())
}
