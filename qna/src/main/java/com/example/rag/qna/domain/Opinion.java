package com.example.rag.qna.domain;

import core.rag.OpinionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Opinion implements OpinionDTO {
    private String opinionId;
    private String questionId;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
