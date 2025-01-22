package com.example.rag.adapter.web.request

import com.example.rag.adapter.web.cleanHtml

data class OpinionUpdateRequest(
    val userId: String,
    val title: String,
    val content: String
) {
    fun cleanData(): OpinionUpdateRequest =
        OpinionUpdateRequest(userId.cleanHtml(), title.cleanHtml(), content.cleanHtml())
}
