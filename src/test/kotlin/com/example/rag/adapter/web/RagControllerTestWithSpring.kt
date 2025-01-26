//package com.example.rag.adapter.web
//
//import com.example.rag.adapter.`in`.web.request.OpinionRequest
//import com.example.rag.adapter.`in`.web.request.OpinionUpdateRequest
//import com.example.rag.adapter.`in`.web.request.QuestionRequest
//import com.example.rag.adapter.`in`.web.request.QuestionUpdateRequest
//import core.rag.RagSystem
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito.*
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//import com.fasterxml.jackson.databind.ObjectMapper
//import core.rag.QuestionDTO
//import org.junit.jupiter.api.DisplayName
//
//@WebMvcTest(RagController::class)
//class RagControllerTestWithSpring {
//
//    @Autowired
//    private lateinit var mockMvc: MockMvc
//
//    private val ragSystem: RagSystem = mock(RagSystem::class.java)
//
//    private val objectMapper = ObjectMapper()
//
//    @BeforeEach
//    fun setUp() {
//    }
//
//    @Test
//    @DisplayName("질문 등록 테스트")
//    fun enrollQuestion() {
//        // Given: 초기 설정
//        val request = QuestionRequest("userId", "질문 제목", "카테고리1", "질문 내용")
//        doNothing().`when`(ragSystem).enrollQuestion(any(), any(), any(), any())
//
//        // When: 동작 수행
//        mockMvc.perform(
//            MockMvcRequestBuilders.post("/api/rag/questions")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
//        )
//        //  Then: 예상 결과 검증
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.content().string("질문 등록 성공!"))
//
//        // 동작 검증
//        verify(ragSystem, times(1)).enrollQuestion(any(), any(), any(), any())
//    }
//
//    @Test
//    @DisplayName("질문 수정 테스트")
//    fun updateQuestion() {
//        // Given
//        val request = QuestionUpdateRequest("userId", "카테고리1", "수정된 질문 제목")
//        doNothing().`when`(ragSystem).updateQuestion(any(), any(), any(), any())
//
//        // When
//        mockMvc.perform(
//            MockMvcRequestBuilders.put("/api/rag/questions/{questionId}", "questionId1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
//        )
//        // Then
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.content().string("질문 수정 성공!"))
//
//        verify(ragSystem, times(1)).updateQuestion(any(), eq("questionId1"), any(), any())
//    }
//
//    @Test
//    @DisplayName("질문 삭제 테스트")
//    fun deleteQuestion() {
//        // Given
//        val userId = "user1"
//        val questionId = "questionId1"
//        doNothing().`when`(ragSystem).deleteQuestion(any(), any())
//
//        // When
//        mockMvc.perform(
//            MockMvcRequestBuilders.delete("/api/rag/questions/{questionId}", questionId)
//                .param("userId", userId)
//        )
//        // Then
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.content().string("질문 삭제 성공!"))
//
//        verify(ragSystem, times(1)).deleteQuestion(eq(userId), eq(questionId))
//    }
//
//    @Test
//    @DisplayName("질문 조회 테스트")
//    fun getQuestion() {
//        // Given
//        val questionId = "questionId1"
//
//        val mockQuestionDTO = mock(QuestionDTO::class.java)
//        `when`(mockQuestionDTO.questionId()).thenReturn(questionId)
//        `when`(mockQuestionDTO.title()).thenReturn("질문 제목")
//        `when`(mockQuestionDTO.category()).thenReturn("카테고리1")
//        `when`(mockQuestionDTO.contents()).thenReturn("질문 내용")
//        `when`(mockQuestionDTO.userId()).thenReturn("user1")
//        `when`(mockQuestionDTO.createdDate()).thenReturn("2025-01-18")
//
//        `when`(ragSystem.readQuestion(any())).thenReturn(mockQuestionDTO)
//
//        // When
//        mockMvc.perform(
//            MockMvcRequestBuilders.get("/api/rag/questions/{questionId}", questionId)
//        )
//        // Then
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("질문 제목"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("카테고리1"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("질문 내용"))
//
//        verify(ragSystem, times(1)).readQuestion(eq(questionId))
//    }
//
//    @Test
//    @DisplayName("의견 등록 테스트")
//    fun enrollOpinion() {
//        // Given
//        val request = OpinionRequest("userId", "questionId1", "의견 제목", "의견 내용")
//        doNothing().`when`(ragSystem).enrollOpinion(any(), any(), any(), any())
//
//        // When
//        mockMvc.perform(
//            MockMvcRequestBuilders.post("/api/rag/opinions")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
//        )
//        // Then
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.content().string("의견 등록 성공!"))
//
//        verify(ragSystem, times(1)).enrollOpinion(any(), any(), any(), any())
//    }
//
//    @Test
//    @DisplayName("의견 수정 테스트")
//    fun updateOpinion() {
//        // Given
//        val request = OpinionUpdateRequest("userId", "수정된 의견 제목", "수정된 의견 내용")
//        doNothing().`when`(ragSystem).updateOpinion(any(), any(), any(), any())
//
//        // When
//        mockMvc.perform(
//            MockMvcRequestBuilders.put("/api/rag/opinions/{opinionId}", "opinionId1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
//        )
//        // Then
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.content().string("의견 수정 성공!"))
//
//        verify(ragSystem, times(1)).updateOpinion(any(), eq("opinionId1"), any(), any())
//    }
//
//    @Test
//    @DisplayName("의견 삭제 테스트")
//    fun deleteOpinion() {
//        // Given
//        val userId = "user1"
//        val opinionId = "opinionId1"
//        doNothing().`when`(ragSystem).deleteOpinion(any(), any())
//
//        // When
//        mockMvc.perform(
//            MockMvcRequestBuilders.delete("/api/rag/opinions/{opinionId}", opinionId)
//                .param("userId", userId)
//        )
//        // Then
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andExpect(MockMvcResultMatchers.content().string("의견 삭제 성공!"))
//
//        verify(ragSystem, times(1)).deleteOpinion(eq(userId), eq(opinionId))
//    }
//}
