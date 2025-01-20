package com.example.rag.qna.domain;

import core.rag.QuestionDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Question implements QuestionDTO {
        private String questionId;
        private final String category;
        private final String title;
        private final String content;
        private final String userId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Question(String category, String title, String content, String userId) {
                this.category = category;
                this.title = title;
                this.content = content;
                this.userId = userId;
        }

        public Question(String questionId, String category, String title, String content, String userId) {
                this(category, title, content, userId);
                this.questionId = questionId;
        }


        public Question(String questionId, String category, String title, String content, String userId, LocalDateTime createdAt) {
                this(questionId, category, title, content, userId);
                this.createdAt = createdAt;
        }

        public Question(String questionId, String category, String title, String content, String userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
                this(questionId, category, title, content, userId, createdAt);
                this.updatedAt = updatedAt;
        }
}
