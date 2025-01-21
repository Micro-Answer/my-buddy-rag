package com.example.rag.qna.domain.cache

import core.rag.Question
import org.springframework.stereotype.Component

@Component
class QuestionCache {
    private val maxSize = 1000
    private val map: MutableMap<String, Question> = LinkedHashMap(maxSize, 0.75f, true)

    fun getRecentQuestion(questionId: String): Question? {
        return map[questionId]
    }

    //  FIFO 구조
    fun putQuestion(questionId: String, question: Question) {
        // 비효율 - 개선 사항
        if (map.size >= maxSize) {
            val oldestKey = map.keys.iterator().next()
            map.remove(oldestKey)
        }
        map[questionId] = question
    }
}