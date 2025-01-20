package com.example.rag.qna.domain;

import core.rag.QuestionDTO;

import java.time.LocalDateTime;

public record Question(
        String userId,
        String questionId,
        String title,
        String category,
        String contents,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) implements QuestionDTO {
}
