package com.example.rag.qna.domain.cache

import core.rag.QuestionTitle
import org.springframework.stereotype.Component

@Component
class CategoryQuestionTitleCache {
    private val caches: MutableMap<String, QuestionTitleCache> = HashMap()

    fun getRecentQuestionTitles(category: String, startNum: Int, endNum: Int): Array<QuestionTitle?>? {
        caches.computeIfAbsent(category) { k: String? -> QuestionTitleCache() }
        return caches[category]?.getRecentQuestionTitles(startNum, endNum)
    }

    fun putRecentQuestionTitle(category: String, questionTitle: QuestionTitle?) {
        caches.computeIfAbsent(category) { k: String? -> QuestionTitleCache() }
        caches[category]!!.putRecentQuestionTitle(questionTitle)
    }
}