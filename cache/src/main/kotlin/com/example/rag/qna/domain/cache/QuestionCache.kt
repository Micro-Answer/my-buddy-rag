package com.example.rag.qna.domain.cache

import core.rag.Question
import org.springframework.stereotype.Component

@Component
class QuestionCache {
    private val maxSize = 1000
    private val cache: MutableMap<String, Question> = LinkedHashMap(maxSize, 0.75f, true)

    fun getRecentQuestion(questionId: String): Question? =
        cache[questionId]

    fun putQuestion(questionId: String, question: Question) {
        cache.trim(maxSize, (maxSize * 0.8F).toInt())
        cache[questionId] = question
    }
}

private fun MutableMap<String, Question>.trim(maxSize: Int, targetSize: Int) {
    if (this.size >= maxSize) {
        this.keys.take(this.size - targetSize)
            .forEach { this.remove(it) }
    }
}