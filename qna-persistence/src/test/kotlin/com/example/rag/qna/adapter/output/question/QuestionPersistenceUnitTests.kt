package com.example.rag.qna.adapter.output.question

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.bean.override.mockito.MockitoBean

@Configuration
@ComponentScan(basePackages = ["com.example.rag.qna.adapter.output.question"])
class TestConfig

@SpringBootTest(classes = [TestConfig::class])
class QuestionPersistenceUnitTests {
    @MockitoBean
    lateinit var questionRepository: QuestionRepository

    @Autowired
    lateinit var questionPersistenceAdapter: QuestionPersistenceAdapter

    @Test
    @DisplayName("test question 영속성 어댑터 initialization")
    fun initialize() {
    }
}