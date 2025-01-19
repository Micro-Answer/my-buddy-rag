package com.example.rag.qna.adapter.output.question;

import java.util.List;
import java.util.Optional;

public interface QuestionPersistencePort {
    void saveQuestion(QuestionEntity questionEntity);
    void updateQuestion(QuestionEntity questionEntity);
    void deleteQuestion(String questionId);
    Optional<QuestionEntity> findQuestionById(String questionId);
    List<QuestionEntity> findQuestionsByUserId(String userId, int offset, int limit);
}
