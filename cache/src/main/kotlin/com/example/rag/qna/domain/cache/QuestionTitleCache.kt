package com.example.rag.qna.domain.cache

import core.rag.QuestionTitle

class QuestionTitleCache {
    private var first = 0
    private val cache: Array<QuestionTitle?> = arrayOfNulls(1000)

    // 비효율 - 개선 사항
    // synchronized 최적화 필요(성능 비교 필요)
    // 수정 삭제 대비 일정 주기마다 최신화
    @Synchronized
    fun getRecentQuestionTitles(offset: Int, limit: Int): List<QuestionTitle?> =
        (0 until limit)
            .map { i -> cache[(first + offset + i) % cache.size] }
            .takeWhile { it != null }

    @Synchronized
    fun putRecentQuestionTitle(questionTitle: QuestionTitle?) {
        first = (first + 1) % cache.size
        cache[first] = questionTitle
    }
}