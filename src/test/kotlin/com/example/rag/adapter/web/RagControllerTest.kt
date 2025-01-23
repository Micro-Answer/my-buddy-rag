package com.example.rag.adapter.web

import com.example.rag.adapter.web.request.*
import core.rag.Opinion
import core.rag.Question
import core.rag.RagSystem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.time.LocalDateTime

@WebMvcTest(RagController::class)
class RagControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockitoBean
    private lateinit var ragSystem: RagSystem

    @BeforeEach
    fun setup() {
    }

    @Test
    @DisplayName("입력 유효성을 모두 만족한 질문 등록 성공")
    fun enrollQuestion1() {
        val title = "Title"
        val request = QuestionRequest("user123", title, "Category", "Content 10자 이상 작성")
        doNothing().`when`(ragSystem).enrollQuestion(anyString(), anyString(), anyString(), anyString())

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .post("/v1/api/rag/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().string("$title 등록 성공!"))
    }

    @Test
    @DisplayName("내용 10자 미만으로 입력 유효성 불만족")
    fun enrollQuestion2() {
        val title = "Title"
        val request = QuestionRequest("user123", title, "Category", "10자 미만")
        doNothing().`when`(ragSystem).enrollQuestion(anyString(), anyString(), anyString(), anyString())

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .post("/v1/api/rag/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    @DisplayName("5자 미만으로 유저 아이디 입력 유효성 불만족")
    fun enrollQuestion3() {
        val title = "Title"
        val request = QuestionRequest("user", title, "Category", "Content 10자 이상 작성")
        doNothing().`when`(ragSystem).enrollQuestion(anyString(), anyString(), anyString(), anyString())

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .post("/v1/api/rag/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    @DisplayName("질문 수정 성공")
    fun updateQuestion1() {
        val request = QuestionUpdateRequest("user123", "Category", "Updated Title", "Updated Content")
        doNothing(). `when`(ragSystem).updateQuestion(anyString(), anyString(), anyString(), anyString(), anyString())

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .put("/v1/api/rag/questions/{questionId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("Updated Title 수정 성공!"))
    }

    @Test
    @DisplayName("질문 삭제 성공")
    fun deleteQuestion1() {
        doNothing().`when`(ragSystem).deleteQuestion(anyString(), anyString())

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .delete("/v1/api/rag/questions/{questionId}", "1")
                .param("userId", "user123")
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

    @Test
    @DisplayName("질문 조회")
    fun readQuestion1() {
        val question = Question("1", "Title", "Content", "Content", "user123", LocalDateTime.now(), LocalDateTime.now())
        `when`(ragSystem.readQuestion(anyString())).thenReturn(question)

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .get("/v1/api/rag/questions/{questionId}", "1")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Content"))
    }

    @Test
    @DisplayName("의견 등록 성공")
    fun enrollOpinion1() {
        val request = OpinionRequest("user123", "1", "Opinion Title", "Opinion Content")
        doNothing().`when`(ragSystem).enrollOpinion(anyString(), anyString(), anyString(), anyString())

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .post("/v1/api/rag/opinions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().string("Opinion Title 등록 성공!"))
    }

    @Test
    @DisplayName("의견 수정 성공")
    fun updateOpinion1() {
        val request = OpinionUpdateRequest("user123", "Updated Title", "Updated Content")
        doNothing().`when`(ragSystem).updateOpinion(anyString(), anyString(), anyString(), anyString())

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .put("/v1/api/rag/opinions/{opinionId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("Updated Title 수정 성공!"))
    }

    @Test
    @DisplayName("의견 삭제 성공")
    fun deleteOpinion1() {
        doNothing().`when`(ragSystem).deleteOpinion(anyString(), anyString())

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .delete("/v1/api/rag/opinions/{opinionId}", "1")
                .param("userId", "user123")
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

    @Test
    @DisplayName("의견 조회 내용 확인")
    fun readOpinions() {
        val opinions = listOf(Opinion("1", "Opinion Title", "Opinion Content", "userId", "1", LocalDateTime.now(), LocalDateTime.now()))
        `when`(ragSystem.readOpinions(anyString(), anyInt(), anyInt())).thenReturn(opinions)

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .get("/v1/api/rag/opinions")
                .param("questionId", "1")
                .param("offset", "0")
                .param("limit", "10")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Opinion Title"))
    }

    @Test
    @DisplayName("검색 내용 확인")
    fun search1() {
        val query = "search query"
        `when`(ragSystem.search(anyString(), anyInt(), anyString(), anyString())).thenReturn("Search Results")

        mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .get("/v1/api/rag/search")
                .param("query", query)
                .param("age", "25")
                .param("gender", "M")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.content().string("Search Results"))
    }
}

//package com.example.rag.adapter.web
//
//import com.example.rag.adapter.web.request.OpinionRequest
//import com.example.rag.adapter.web.request.OpinionUpdateRequest
//import com.example.rag.adapter.web.request.QuestionRequest
//import com.example.rag.adapter.web.request.QuestionUpdateRequest
//import core.rag.RagSystem
//import core.rag.Question
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.DisplayName
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito.*
//import kotlin.test.assertEquals
//
//class RagControllerTest {
//
//    private lateinit var ragController: RagController
//    private val ragSystem: RagSystem = mock(RagSystem::class.java)
//
//    @BeforeEach
//    fun setUp() {
//        ragController = RagController(ragSystem) // 컨트롤러 생성
//    }
//
//    @Test
//    @DisplayName("질문 등록 테스트")
//    fun enrollQuestion() {
//        // Given
//        val request = QuestionRequest("userId", "질문 제목", "카테고리1", "질문 내용")
//        `when`(ragSystem.enrollQuestion(any(), any(), any(), any())).thenReturn("질문 등록")
//
//        // When
//        val response = ragController.enrollQuestion(request)
//
//        // Then
//        assertEquals(200, response.statusCode.value()) // HTTP 상태 코드 검증
//        assertEquals("질문 등록 성공!", response.body) // 응답 본문 검증
//        verify(ragSystem, times(1)).enrollQuestion(
//            eq("userId"), eq("질문 제목"), eq("카테고리1"), eq("질문 내용")
//        )
//    }
//
//    @Test
//    @DisplayName("질문 수정 테스트")
//    fun updateQuestion() {
//        // Given
//        val questionId = "questionId1"
//        val request = QuestionUpdateRequest("userId", "카테고리1", "수정된 질문 제목")
//        `when`(ragSystem.updateQuestion(any(), any(), any(), any())).thenReturn("질문 수정")
//
//        // When
//        val response = ragController.updateQuestion(questionId, request)
//
//        // Then
//        assertEquals(200, response.statusCode.value())
//        assertEquals("질문 수정 성공!", response.body)
//        verify(ragSystem, times(1)).updateQuestion(
//            eq("userId"), eq(questionId), eq("카테고리1"), eq("수정된 질문 제목")
//        )
//    }
//
//    @Test
//    @DisplayName("질문 삭제 테스트")
//    fun deleteQuestion() {
//        // Given
//        val userId = "user1"
//        val questionId = "questionId1"
//        `when`(ragSystem.deleteQuestion(any(), any())).thenReturn("질문 삭제")
//
//        // When
//        val response = ragController.deleteQuestion(questionId, userId)
//
//        // Then
//        assertEquals(200, response.statusCode.value())
//        assertEquals("질문 삭제 성공!", response.body)
//        verify(ragSystem, times(1)).deleteQuestion(eq(userId), eq(questionId))
//    }
//
//    @Test
//    @DisplayName("질문 조회 테스트")
//    fun getQuestion() {
//        // Given
//        val questionId = "questionId1"
//        val mockQuestion = mock(Question::class.java)
//        `when`(mockQuestion.userId()).thenReturn("user1")
//        `when`(mockQuestion.questionId()).thenReturn(questionId)
//        `when`(mockQuestion.title()).thenReturn("질문 제목")
//        `when`(mockQuestion.category()).thenReturn("카테고리1")
//        `when`(mockQuestion.contents()).thenReturn("질문 내용")
//        `when`(mockQuestion.createdDate()).thenReturn("2025-01-18")
//        `when`(ragSystem.readQuestion(eq(questionId))).thenReturn(mockQuestion)
//
//        // When
//        val response = ragController.readQuestion(questionId)
//
//        // Then
//        assertEquals(200, response.statusCode.value())
//        assertEquals("user1", response.body?.userId())
//        assertEquals(questionId, response.body?.questionId())
//        assertEquals("질문 제목", response.body?.title())
//        assertEquals("카테고리1", response.body?.category())
//        assertEquals("질문 내용", response.body?.contents())
//        assertEquals("2025-01-18", response.body?.createdDate())
//        verify(ragSystem, times(1)).readQuestion(eq(questionId))
//    }
//
//    @Test
//    @DisplayName("의견 등록 테스트")
//    fun enrollOpinion() {
//        // Given
//        val request = OpinionRequest("userId", "questionId1", "의견 제목", "의견 내용")
//        `when`(ragSystem.enrollOpinion(any(), any(), any(), any())).thenReturn("의견 등록")
//
//        // When
//        val response = ragController.enrollOpinion(request)
//
//        // Then
//        assertEquals(200, response.statusCode.value())
//        assertEquals("의견 등록 성공!", response.body)
//        verify(ragSystem, times(1)).enrollOpinion(
//            eq("userId"), eq("questionId1"), eq("의견 제목"), eq("의견 내용")
//        )
//    }
//
//    @Test
//    @DisplayName("의견 수정 테스트")
//    fun updateOpinion() {
//        // Given
//        val opinionId = "opinionId1"
//        val request = OpinionUpdateRequest("userId", "수정된 의견 제목", "수정된 의견 내용")
//        `when`(ragSystem.updateOpinion(any(), any(), any(), any())).thenReturn("의견 수정")
//
//        // When
//        val response = ragController.updateOpinion(opinionId, request)
//
//        // Then
//        assertEquals(200, response.statusCode.value())
//        assertEquals("의견 수정 성공!", response.body)
//        verify(ragSystem, times(1)).updateOpinion(
//            eq("userId"), eq(opinionId), eq("수정된 의견 제목"), eq("수정된 의견 내용")
//        )
//    }
//
//    @Test
//    @DisplayName("의견 삭제 테스트")
//    fun deleteOpinion() {
//        // Given
//        val userId = "user1"
//        val opinionId = "opinionId1"
//        `when`(ragSystem.deleteOpinion(any(), any())).thenReturn("의견 삭제")
//
//        // When
//        val response = ragController.deleteOpinion(opinionId, userId)
//
//        // Then
//        assertEquals(200, response.statusCode.value())
//        assertEquals("의견 삭제 성공!", response.body)
//        verify(ragSystem, times(1)).deleteOpinion(eq(userId), eq(opinionId))
//    }
//}
