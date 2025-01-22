package com.example.rag.adapter.web.request

data class QuestionUpdateRequest(
    val userId: String,
    val category: String,
    val title: String,
    val content: String
)