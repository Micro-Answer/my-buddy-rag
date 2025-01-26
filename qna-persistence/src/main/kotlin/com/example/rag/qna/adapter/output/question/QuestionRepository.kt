package com.example.rag.qna.adapter.output.question

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface QuestionRepository: JpaRepository<QuestionEntity, Long> {
    @Query("SELECT q.userId FROM QuestionEntity q WHERE q.id = :questionId")
    fun getUserIdByQuestionId(questionId: Long): String?

    @Query("SELECT q FROM QuestionEntity q WHERE q.category = :category ORDER BY q.createdAt DESC")
    fun findByCategory(category: String, pageable: Pageable): Page<QuestionEntity>

    @Query("SELECT q FROM QuestionEntity q WHERE q.userId = :userId ORDER BY q.createdAt DESC")
    fun findByUserId(userId: String, pageable: Pageable): Page<QuestionEntity>

    /**
     * 사용자 ID로 질문 목록 조회 (최신순 정렬)
     *
     * @param userId 사용자 ID
     * @return 사용자별 질문 목록
     */
    @Query("SELECT q FROM QuestionEntity q WHERE q.userId = :userId ORDER BY q.createdAt DESC")
    fun findByUserId(userId: String): List<QuestionEntity>

    /**
     * 특정 기간 내 질문 조회 (최신순 정렬)
     *
     * @param startDate 조회 시작일
     * @param endDate 조회 종료일
     * @return 기간 내 질문 목록
     */
    @Query("SELECT q FROM QuestionEntity q WHERE q.createdAt BETWEEN :startDate AND :endDate ORDER BY q.createdAt DESC")
    fun findByCreatedAtBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<QuestionEntity>

    /**
     * 제목에 키워드가 포함된 질문 검색 (최신순 정렬)
     *
     * @param keyword 제목 검색 키워드
     * @return 제목에 키워드가 포함된 질문 목록
     */
    @Query("SELECT q FROM QuestionEntity q WHERE q.title LIKE %:keyword% ORDER BY q.createdAt DESC")
    fun findByTitleContaining(keyword: String): List<QuestionEntity>

    /**
     * 제목과 내용에 키워드가 포함된 질문 검색 (최신순 정렬)
     *
     * @param keyword 검색 키워드
     * @return 제목 또는 내용에 키워드가 포함된 질문 목록
     */
    @Query("SELECT q FROM QuestionEntity q WHERE q.title LIKE %:keyword% OR q.content LIKE %:keyword% ORDER BY q.createdAt DESC")
    fun findByTitleOrContentContaining(keyword: String): List<QuestionEntity>

    /**
     * 페이징을 위한 질문 조회
     *
     * @param offset 시작 인덱스
     * @param limit 가져올 질문 수
     * @return 질문 목록
     */
    @Query(value = "SELECT * FROM questions ORDER BY created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    fun findAllWithPagination(offset: Int, limit: Int): List<QuestionEntity>
}