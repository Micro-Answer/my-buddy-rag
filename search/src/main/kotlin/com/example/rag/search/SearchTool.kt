package com.example.rag.search

import core.rag.event.QnAEvent
import core.search.SearchSystem

class SearchTool: SearchSystem {

    override fun search(query: String): String =
        query.also { println("$it 검색했습니다") }

    override fun search(query: String, category: String): String =
        query.also { println("검색 문장 : $it, 카테고리 : $category . 검색했습니다") }

    override fun enrollQuestion(questionId: String, event: QnAEvent.EnrollQuestion): String =
        "enrollQuestion"

    override fun updateQuestion(event: QnAEvent.UpdateQuestion): String =
        "updateQuestion"

    override fun deleteQuestion(event: QnAEvent.DeleteQuestion): String =
        "deleteQuestion"
}