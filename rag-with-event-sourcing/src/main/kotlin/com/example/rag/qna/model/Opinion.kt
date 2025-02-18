package com.example.rag.qna.model

import java.time.LocalDateTime
import java.util.*

data class Opinion (
    val id: UUID,
    val title: String,
    val content: String,
    val userId: String,
    val questionId: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)

