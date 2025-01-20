package com.example.rag.application

import core.qna.QnaSystem
import core.search.SearchSystem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.Mockito.*

@ExtendWith(MockitoExtension::class)
class RagUnitTests {

    @Mock
    lateinit var qnaSystem: QnaSystem // QnaSystem Mock 객체 생성

    @Mock
    lateinit var searchSystem: SearchSystem // SearchSystem Mock 객체 생성

//    @InjectMocks
    lateinit var searchableQnA: SearchableQnA // Mock된 QnaSystem과 SearchSystem 주입

//    @InjectMocks
    lateinit var rag: Rag // Mock된 SearchableQnA 주입


    @BeforeEach
    fun setUp() {
        // Mock 객체를 수동으로 초기화
        searchableQnA = SearchableQnA(qnaSystem, searchSystem)
        rag = Rag(searchableQnA)
    }

    @Test
    @DisplayName("test Rag initialization")
    fun initialize() {
    }
}
