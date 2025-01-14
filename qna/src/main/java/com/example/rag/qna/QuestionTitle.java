package com.example.rag.qna;

import core.rag.QuestionTitleDTO;

public record QuestionTitle(String questionId, String title, String userId, String createdDate) implements QuestionTitleDTO {
}
