package com.example.rag.qna.domain;

import core.rag.QuestionTitleDTO;

import java.time.LocalDateTime;

public record QuestionTitle(String questionId, String title, String userId, LocalDateTime createdAt) implements QuestionTitleDTO {
}
