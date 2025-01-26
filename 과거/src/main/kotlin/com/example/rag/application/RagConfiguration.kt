package com.example.rag.application

import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.rag.RagSystem
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RagConfiguration {
    @Bean
    fun searchableQnA(qna: QnaSystem, search: SearchSystem): SearchableQnA =
        SearchableQnA(qna, search)

    @Bean
    fun ragSystem(searchableQnA: SearchableQnA, explainer: ExplainerSystem): RagSystem =
        Rag(searchableQnA, explainer)
}