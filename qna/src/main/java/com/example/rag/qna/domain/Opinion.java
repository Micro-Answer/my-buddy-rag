package com.example.rag.qna.domain;

import core.rag.OpinionDTO;

import java.time.LocalDateTime;

public record Opinion(String userId,
                      String questionId,
                      String opinionId,
                      String title,
                      String contents,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) implements OpinionDTO {
}
