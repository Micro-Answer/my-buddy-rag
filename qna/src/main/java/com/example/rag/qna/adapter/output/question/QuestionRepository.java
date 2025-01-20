package com.example.rag.qna.adapter.output.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query("SELECT q FROM QuestionEntity q WHERE q.userId = :userId ORDER BY q.createdAt DESC")
    List<QuestionEntity> findByUserId(String userId, int offset, int limit);

    /**
     * 사용자 ID로 질문 목록 조회 (최신순 정렬)
     *
     * @param userId 사용자 ID
     * @return 사용자별 질문 목록
     */
    @Query("SELECT q FROM QuestionEntity q WHERE q.userId = :userId ORDER BY q.createdAt DESC")
    List<QuestionEntity> findByUserId(String userId);

    /**
     * 특정 기간 내 질문 조회 (최신순 정렬)
     *
     * @param startDate 조회 시작일
     * @param endDate 조회 종료일
     * @return 기간 내 질문 목록
     */
    @Query("SELECT q FROM QuestionEntity q WHERE q.createdAt BETWEEN :startDate AND :endDate ORDER BY q.createdAt DESC")
    List<QuestionEntity> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * 제목에 키워드가 포함된 질문 검색 (최신순 정렬)
     *
     * @param keyword 제목 검색 키워드
     * @return 제목에 키워드가 포함된 질문 목록
     */
    @Query("SELECT q FROM QuestionEntity q WHERE q.title LIKE %:keyword% ORDER BY q.createdAt DESC")
    List<QuestionEntity> findByTitleContaining(String keyword);

    /**
     * 제목과 내용에 키워드가 포함된 질문 검색 (최신순 정렬)
     *
     * @param keyword 검색 키워드
     * @return 제목 또는 내용에 키워드가 포함된 질문 목록
     */
    @Query("SELECT q FROM QuestionEntity q WHERE q.title LIKE %:keyword% OR q.content LIKE %:keyword% ORDER BY q.createdAt DESC")
    List<QuestionEntity> findByTitleOrContentContaining(String keyword);

    /**
     * 페이징을 위한 질문 조회
     *
     * @param offset 시작 인덱스
     * @param limit 가져올 질문 수
     * @return 질문 목록
     */
    @Query(value = "SELECT * FROM questions ORDER BY created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<QuestionEntity> findAllWithPagination(int offset, int limit);
}
