package com.example.rag.qna.adapter.output.question;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@Getter
@Setter
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Version
    private int version; // 낙관적 락

    public QuestionEntity(String category, String title, String content, String userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
