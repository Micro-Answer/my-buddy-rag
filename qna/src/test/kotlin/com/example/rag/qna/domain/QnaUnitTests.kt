package com.example.rag.qna.domain

import com.example.rag.qna.adapter.output.opinion.OpinionPersistencePort
import com.example.rag.qna.adapter.output.question.QuestionPersistencePort
import core.qna.QnaSystem
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(classes = [QnAConfiguration::class])
class QnaUnitTests {
    @MockitoBean
    lateinit var questionPersistence: QuestionPersistencePort

    @MockitoBean
    lateinit var opinionPersistence: OpinionPersistencePort

    @Autowired
    lateinit var qna: QnaSystem

    @Test
    @DisplayName("test QnA initialization")
    fun initialize() {
    }
}