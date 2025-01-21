package com.example.rag.adapter.web

import com.example.rag.adapter.web.request.OpinionRequest
import com.example.rag.adapter.web.request.OpinionUpdateRequest
import com.example.rag.adapter.web.request.QuestionRequest
import com.example.rag.adapter.web.request.QuestionUpdateRequest
import core.rag.RagSystem
import core.rag.Question
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

class RagControllerTest {

    private lateinit var ragController: RagController
    private val ragSystem: RagSystem = mock(RagSystem::class.java)

    @BeforeEach
    fun setUp() {
        ragController = RagController(ragSystem) // 컨트롤러 생성
    }

    @Test
    @DisplayName("질문 등록 테스트")
    fun enrollQuestion() {
        // Given
        val request = QuestionRequest("userId", "질문 제목", "카테고리1", "질문 내용")
        `when`(ragSystem.enrollQuestion(any(), any(), any(), any())).thenReturn("질문 등록")

        // When
        val response = ragController.enrollQuestion(request)

        // Then
        assertEquals(200, response.statusCode.value()) // HTTP 상태 코드 검증
        assertEquals("질문 등록 성공!", response.body) // 응답 본문 검증
        verify(ragSystem, times(1)).enrollQuestion(
            eq("userId"), eq("질문 제목"), eq("카테고리1"), eq("질문 내용")
        )
    }

    @Test
    @DisplayName("질문 수정 테스트")
    fun updateQuestion() {
        // Given
        val questionId = "questionId1"
        val request = QuestionUpdateRequest("userId", "카테고리1", "수정된 질문 제목")
        `when`(ragSystem.updateQuestion(any(), any(), any(), any())).thenReturn("질문 수정")

        // When
        val response = ragController.updateQuestion(questionId, request)

        // Then
        assertEquals(200, response.statusCode.value())
        assertEquals("질문 수정 성공!", response.body)
        verify(ragSystem, times(1)).updateQuestion(
            eq("userId"), eq(questionId), eq("카테고리1"), eq("수정된 질문 제목")
        )
    }

    @Test
    @DisplayName("질문 삭제 테스트")
    fun deleteQuestion() {
        // Given
        val userId = "user1"
        val questionId = "questionId1"
        `when`(ragSystem.deleteQuestion(any(), any())).thenReturn("질문 삭제")

        // When
        val response = ragController.deleteQuestion(questionId, userId)

        // Then
        assertEquals(200, response.statusCode.value())
        assertEquals("질문 삭제 성공!", response.body)
        verify(ragSystem, times(1)).deleteQuestion(eq(userId), eq(questionId))
    }

    @Test
    @DisplayName("질문 조회 테스트")
    fun getQuestion() {
        // Given
        val questionId = "questionId1"
        val mockQuestion = mock(Question::class.java)
        `when`(mockQuestion.userId()).thenReturn("user1")
        `when`(mockQuestion.questionId()).thenReturn(questionId)
        `when`(mockQuestion.title()).thenReturn("질문 제목")
        `when`(mockQuestion.category()).thenReturn("카테고리1")
        `when`(mockQuestion.contents()).thenReturn("질문 내용")
        `when`(mockQuestion.createdDate()).thenReturn("2025-01-18")
        `when`(ragSystem.readQuestion(eq(questionId))).thenReturn(mockQuestion)

        // When
        val response = ragController.readQuestion(questionId)

        // Then
        assertEquals(200, response.statusCode.value())
        assertEquals("user1", response.body?.userId())
        assertEquals(questionId, response.body?.questionId())
        assertEquals("질문 제목", response.body?.title())
        assertEquals("카테고리1", response.body?.category())
        assertEquals("질문 내용", response.body?.contents())
        assertEquals("2025-01-18", response.body?.createdDate())
        verify(ragSystem, times(1)).readQuestion(eq(questionId))
    }

    @Test
    @DisplayName("의견 등록 테스트")
    fun enrollOpinion() {
        // Given
        val request = OpinionRequest("userId", "questionId1", "의견 제목", "의견 내용")
        `when`(ragSystem.enrollOpinion(any(), any(), any(), any())).thenReturn("의견 등록")

        // When
        val response = ragController.enrollOpinion(request)

        // Then
        assertEquals(200, response.statusCode.value())
        assertEquals("의견 등록 성공!", response.body)
        verify(ragSystem, times(1)).enrollOpinion(
            eq("userId"), eq("questionId1"), eq("의견 제목"), eq("의견 내용")
        )
    }

    @Test
    @DisplayName("의견 수정 테스트")
    fun updateOpinion() {
        // Given
        val opinionId = "opinionId1"
        val request = OpinionUpdateRequest("userId", "수정된 의견 제목", "수정된 의견 내용")
        `when`(ragSystem.updateOpinion(any(), any(), any(), any())).thenReturn("의견 수정")

        // When
        val response = ragController.updateOpinion(opinionId, request)

        // Then
        assertEquals(200, response.statusCode.value())
        assertEquals("의견 수정 성공!", response.body)
        verify(ragSystem, times(1)).updateOpinion(
            eq("userId"), eq(opinionId), eq("수정된 의견 제목"), eq("수정된 의견 내용")
        )
    }

    @Test
    @DisplayName("의견 삭제 테스트")
    fun deleteOpinion() {
        // Given
        val userId = "user1"
        val opinionId = "opinionId1"
        `when`(ragSystem.deleteOpinion(any(), any())).thenReturn("의견 삭제")

        // When
        val response = ragController.deleteOpinion(opinionId, userId)

        // Then
        assertEquals(200, response.statusCode.value())
        assertEquals("의견 삭제 성공!", response.body)
        verify(ragSystem, times(1)).deleteOpinion(eq(userId), eq(opinionId))
    }
}
