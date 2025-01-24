package com.example.rag.qna.domain.cache

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(classes = [CacheConfiguration::class])
class CacheUnitTests {
    @MockitoBean
    lateinit var questionCache: QuestionCache

    @MockitoBean
    lateinit var categoryQuestionTitleCache: CategoryQuestionTitleCache

    @Autowired
    lateinit var cache: Cache

    @Test
    @DisplayName("test Cache initialization")
    fun initialize() {
    }
}