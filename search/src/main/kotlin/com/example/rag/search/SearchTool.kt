package com.example.rag.search

import core.search.SearchSystem

class SearchTool: SearchSystem {

    override fun search(query: String): String =
        query.also { println("$it 검색했습니다") }

    override fun search(query: String, category: String): String =
        query.also { println("검색 문장 : $it, 카테고리 : $category . 검색했습니다") }

    override fun enrollQuestion(questionId: String, title: String, category: String, content: String): String =
        "enrollQuestion"

    override fun updateQuestion(questionId: String, title: String, category: String, content: String): String =
        "updateQuestion"

    override fun deleteQuestion(questionId: String): String =
        "deleteQuestion"
}