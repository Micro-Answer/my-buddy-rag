package com.example.rag.qna.adapter.output.question;

import com.example.rag.qna.domain.Question;

import java.util.List;

public interface QuestionPersistencePort {
    void saveQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestion(String questionId);
    Question findQuestionById(String questionId);
    List<Question> findQuestionsByUserId(String userId, int offset, int limit);
}
