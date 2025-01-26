package com.example.rag.adapter.web

import com.example.rag.adapter.web.request.*
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.rag.RagSystem
import core.rag.event.QnAEvent
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/v1/api/rag")
class RagController(private val rag: RagSystem) {

    @PostMapping("/questions")
    fun enrollQuestion(@RequestBody @Valid request: QuestionRequest): ResponseEntity<String> {
        rag.execute(request.cleanData().toEvent())
        return ResponseEntity.status(HttpStatus.CREATED).body("${request.title} 등록 성공!")
    }

    @PutMapping("/questions/{questionId}")
    fun updateQuestion(
        @PathVariable questionId: String,
        @RequestBody @Valid request: QuestionUpdateRequest
    ): ResponseEntity<String> {
        rag.execute(request.cleanData().toEvent(questionId))
        return ResponseEntity.ok("${request.title} 수정 성공!")
    }

    @DeleteMapping("/questions/{questionId}")
    fun deleteQuestion(
        @PathVariable questionId: String,
        @RequestParam userId: String
    ): ResponseEntity<Void> {
        rag.execute(QnAEvent.DeleteQuestion(userId.cleanHtml(), questionId.cleanHtml()))
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/questions/{questionId}")
    fun readQuestion(@PathVariable questionId: String): ResponseEntity<Question> =
        ResponseEntity.ok(rag.readQuestion(questionId.cleanHtml()))

    @GetMapping("/questions/titles")
    fun readQuestionTitles(
        @RequestParam category: String,
        @RequestParam offset: Int,
        @RequestParam limit: Int
    ): ResponseEntity<List<QuestionTitle>> =
        ResponseEntity.ok(rag.readQuestionTitles(category.cleanHtml(), offset, limit))

    @PostMapping("/opinions")
    fun enrollOpinion(@RequestBody @Valid request: OpinionRequest): ResponseEntity<String> {
        rag.execute(request.cleanData().toEvent())
        return ResponseEntity.status(HttpStatus.CREATED).body("${request.title} 등록 성공!")
    }

    @PutMapping("/opinions/{opinionId}")
    fun updateOpinion(
        @PathVariable opinionId: String,
        @RequestBody @Valid request: OpinionUpdateRequest
    ): ResponseEntity<String> {
        rag.execute(request.cleanData().toEvent(opinionId))
        return ResponseEntity.ok("${request.title} 수정 성공!")
    }

    @DeleteMapping("/opinions/{opinionId}")
    fun deleteOpinion(
        @PathVariable opinionId: String,
        @RequestParam userId: String
    ): ResponseEntity<Void> {
        rag.execute(QnAEvent.DeleteOpinion(userId.cleanHtml(), opinionId.cleanHtml()))
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/opinions")
    fun readOpinions(
        @RequestParam questionId: String,
        @RequestParam offset: Int,
        @RequestParam limit: Int
    ): ResponseEntity<List<Opinion>> =
        ResponseEntity.ok(rag.readOpinions(questionId.cleanHtml(), offset, limit))

    // 맞춤형 해설 질의응답 검색
    @GetMapping("/search")
    fun search(
        @RequestParam query: String,
        @RequestParam age: Int,
        @RequestParam(required = false) gender: String?,
        @RequestParam(required = false) personalData: String?
    ): ResponseEntity<String> =
        ResponseEntity.ok(rag.search(query.cleanHtml(), age, gender?.cleanHtml(), personalData?.cleanHtml()))
}
