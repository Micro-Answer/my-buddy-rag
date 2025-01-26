package com.example.rag.qna.domain.cache

import core.qna.CacheSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CacheConfiguration {
    @Bean
    fun questionCache(): QuestionCache =
        QuestionCache()

    @Bean
    fun categoryQuestionTitleCache(): CategoryQuestionTitleCache =
        CategoryQuestionTitleCache()

    @Bean
    fun cache(questionTitleCache: CategoryQuestionTitleCache, questionCache: QuestionCache): CacheSystem =
        Cache(questionTitleCache, questionCache)
}