package com.example.rag.adapter.`in`.web

import com.example.rag.adapter.`in`.web.request.OpinionRequest
import com.example.rag.adapter.`in`.web.request.OpinionUpdateRequest
import com.example.rag.adapter.`in`.web.request.QuestionRequest
import com.example.rag.adapter.`in`.web.request.QuestionUpdateRequest
import core.rag.OpinionDTO
import core.rag.QuestionDTO
import core.rag.QuestionTitleDTO
import core.rag.RagSystem
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/rag")
class RagController(private val rag: RagSystem) {

    // 질문 등록
    @PostMapping("/questions")
    fun enrollQuestion(@RequestBody request: QuestionRequest): ResponseEntity<String> {
        rag.enrollQuestion(request.userId, request.title, request.category, request.content)
        return ResponseEntity.ok("질문 등록 성공!")
    }

    // 질문 수정
    @PutMapping("/questions/{questionId}")
    fun updateQuestion(@PathVariable questionId: String, @RequestBody request: QuestionUpdateRequest): ResponseEntity<String> {
        rag.updateQuestion(request.userId, questionId, request.category, request.title)
        return ResponseEntity.ok("질문 수정 성공!")
    }

    // 질문 삭제
    @DeleteMapping("/questions/{questionId}")
    fun deleteQuestion(@PathVariable questionId: String, @RequestParam userId: String): ResponseEntity<String> {
        rag.deleteQuestion(userId, questionId)
        return ResponseEntity.ok("질문 삭제 성공!")
    }

    // 질문 조회
    @GetMapping("/questions/{questionId}")
    fun readQuestion(@PathVariable questionId: String): ResponseEntity<QuestionDTO> {
        return ResponseEntity.ok(rag.readQuestion(questionId))
    }

    // 질문 타이틀 목록 조회
    @GetMapping("/questions/titles")
    fun readQuestionTitles(@RequestParam category: String?, @RequestParam start: Int, @RequestParam end: Int): ResponseEntity<Array<QuestionTitleDTO>> {
        return ResponseEntity.ok(rag.readQuestionTitles(category, start, end))
    }

    // 의견 등록
    @PostMapping("/opinions")
    fun enrollOpinion(@RequestBody request: OpinionRequest): ResponseEntity<String> {
        rag.enrollOpinion(request.userId, request.questionId, request.title, request.content)
        return ResponseEntity.ok("의견 등록 성공!")
    }

    // 의견 수정
    @PutMapping("/opinions/{opinionId}")
    fun updateOpinion(@PathVariable opinionId: String, @RequestBody request: OpinionUpdateRequest): ResponseEntity<String> {
        rag.updateOpinion(request.userId, opinionId, request.title, request.content)
        return ResponseEntity.ok("의견 수정 성공!")
    }

    // 의견 삭제
    @DeleteMapping("/opinions/{opinionId}")
    fun deleteOpinion(@PathVariable opinionId: String, @RequestParam userId: String): ResponseEntity<String> {
        rag.deleteOpinion(userId, opinionId)
        return ResponseEntity.ok("의견 삭제 성공!")
    }

    // 의견 목록 조회
    @GetMapping("/opinions")
    fun readOpinions(@RequestParam questionId: String, @RequestParam start: Int, @RequestParam end: Int): ResponseEntity<Array<OpinionDTO>> {
        return ResponseEntity.ok(rag.readOpinions(questionId, start, end))
    }

    // 맞춤형 해설 질의응답 검색
    @GetMapping("/search")
    fun search(@RequestParam query: String, @RequestParam age: Int,
               @RequestParam(required = false) gender: String?, @RequestParam(required = false) personalData: String?): ResponseEntity<String> {
        return ResponseEntity.ok(rag.search(query, age, gender, personalData))
    }
}
