package com.example.rag.qna.adapter.output.opinion

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.bean.override.mockito.MockitoBean

@Configuration
@ComponentScan(basePackages = ["com.example.rag.qna.adapter.output.opinion"])
class TestConfig

@SpringBootTest(classes = [TestConfig::class])
class OpinionPersistenceUnitTests {
    @MockitoBean
    lateinit var opinionRepository: OpinionRepository

    @Autowired
    lateinit var opinionPersistenceAdapter: OpinionPersistenceAdapter

    @Test
    @DisplayName("test opinion 영속성 어댑터 initialization")
    fun initialize() {
    }
}