package com.example.rag.qna.domain.cache

import core.qna.CacheSystem
import core.rag.Question
import core.rag.QuestionTitle
import org.springframework.stereotype.Component

@Component
class Cache(private val questionTitleCache: CategoryQuestionTitleCache, private val questionCache: QuestionCache): CacheSystem {

    override fun getRecentQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle?>
        = questionTitleCache.getRecentQuestionTitles(category, offset, limit)

    override fun putRecentQuestionTitle(category: String, questionTitle: QuestionTitle) {
        questionTitleCache.putRecentQuestionTitle(category, questionTitle)
    }

    override fun getRecentQuestion(questionId: String): Question?
        = questionCache.getRecentQuestion(questionId)

    override fun putRecentQuestion(questionId: String, question: Question) {
        questionCache.putQuestion(questionId, question)
    }
}