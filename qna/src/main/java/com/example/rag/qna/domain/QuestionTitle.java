package com.example.rag.qna.domain;

import core.rag.QuestionTitleDTO;

public record QuestionTitle(String questionId, String title, String userId, String createdDate) implements QuestionTitleDTO {
}
