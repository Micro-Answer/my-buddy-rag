package com.example.rag.application.event.opinion.publisher

data class OpinionEvent(
    val opinionId: String,
    val eventType: String,
    val title: String,
    val content: String,
    val userId: String,
    val questionId: String
)