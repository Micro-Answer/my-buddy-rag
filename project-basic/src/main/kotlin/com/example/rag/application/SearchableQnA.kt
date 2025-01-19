package com.example.rag.application

import core.qna.QnaSystem
import core.search.SearchSystem
import org.springframework.stereotype.Component

@Component
class SearchableQnA(private val qna: QnaSystem, private val search: SearchSystem) {
    init {
        println("create SearchableQnA")
    }
}