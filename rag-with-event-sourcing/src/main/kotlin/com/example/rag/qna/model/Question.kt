package com.example.rag.qna.model

import java.time.LocalDateTime
import java.util.UUID

data class Question(
    val id: UUID,
    val category: String,
    val title: String,
    val content: String,
    val userId: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)