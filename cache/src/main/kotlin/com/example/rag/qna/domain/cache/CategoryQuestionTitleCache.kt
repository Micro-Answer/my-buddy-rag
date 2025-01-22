package com.example.rag.qna.domain.cache

import core.rag.QuestionTitle
import org.springframework.stereotype.Component

@Component
class CategoryQuestionTitleCache {
    private val caches: MutableMap<String, QuestionTitleCache> = HashMap()

    fun getRecentQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle?>
        = caches[category]?.getRecentQuestionTitles(offset, limit) ?: listOf()

    fun putRecentQuestionTitle(category: String, questionTitle: QuestionTitle) {
        caches.computeIfAbsent(category) { QuestionTitleCache() }
        caches[category]!!.putRecentQuestionTitle(questionTitle)
    }
}