package com.example.rag.qna.domain.cache

import core.rag.QuestionTitle

class QuestionTitleCache() {
    private var first = 0
    private val cache: Array<QuestionTitle?> = arrayOfNulls(1000)

    // 비효율 - 개선 사항
    // synchronized 최적화 필요(성능 비교 필요)
    // 수정 삭제 대비 일정 주기마다 최신화
    @Synchronized
    fun getRecentQuestionTitles(startNum: Int, endNum: Int): Array<QuestionTitle?> {
        val response = arrayOfNulls<QuestionTitle>(endNum - startNum + 1)

        for (i in startNum..endNum) {
            val current = cache[(first + i) % cache.size] ?: return response
            response[i - startNum] = current
        }

        return response
    }

    @Synchronized
    fun putRecentQuestionTitle(questionTitle: QuestionTitle?) {
        first = (first + 1) % cache.size
        cache[first] = questionTitle
    }
}