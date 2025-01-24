package com.example.rag.application

import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.rag.RagSystem
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Configuration
class RagConfiguration {
    private val nThreads = 4

    @Bean
    fun executorService(): ExecutorService {
        return Executors.newFixedThreadPool(nThreads)
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun searchableQnA(qna: QnaSystem, search: SearchSystem, executorService: ExecutorService): SearchableQnA =
        SearchableQnA(qna, search, executorService, nThreads)

    @Bean
    fun ragSystem(searchableQnA: SearchableQnA, explainer: ExplainerSystem): RagSystem =
        Rag(searchableQnA, explainer)
}