package com.example.rag.application

import com.example.rag.application.command.consumer.DeleteQuestionConsumer
import com.example.rag.application.configuration.RagConfiguration
import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.rag.RagSystem
import core.search.SearchSystem
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(classes = [RagConfiguration::class])
class RagUnitTests {
    @MockitoBean
    lateinit var qna: QnaSystem

    @MockitoBean
    lateinit var search: SearchSystem

    @Autowired
    lateinit var searchableQnA: DeleteQuestionConsumer

    @MockitoBean
    lateinit var explainer: ExplainerSystem

    @Autowired
    lateinit var rag: RagSystem

    @Test
    @DisplayName("test Rag initialization")
    fun initialize() {
    }
}