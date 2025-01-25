package com.example.rag.application

import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.search.SearchSystem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RagUnitTests {
    @Mock
    lateinit var qna: QnaSystem

    @Mock
    lateinit var search: SearchSystem

    @Mock
    lateinit var explainer: ExplainerSystem

    @InjectMocks
    lateinit var searchableQnA: SearchableQnA

    lateinit var rag: Rag


    @BeforeEach
    fun setUp() {
        rag = Rag(searchableQnA, explainer)
    }

    @Test
    @DisplayName("test Rag initialization")
    fun initialize() {
    }
}
