package com.example.rag.qna.adapter.output.question;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class QuestionPersistenceAdapter implements QuestionPersistencePort {

    private final QuestionRepository questionRepository;

    @Override
    public void saveQuestion(QuestionEntity questionEntity) {
        questionRepository.save(questionEntity);
    }

    @Override
    public void updateQuestion(QuestionEntity questionEntity) {
        questionRepository.save(questionEntity);
    }

    @Override
    public void deleteQuestion(String questionId) {
        questionRepository.deleteById(Long.parseLong(questionId));
    }

    @Override
    public Optional<QuestionEntity> findQuestionById(String questionId) {
        return questionRepository.findById(Long.parseLong(questionId));
    }

    @Override
    public List<QuestionEntity> findQuestionsByUserId(String userId, int offset, int limit) {
        return questionRepository.findByUserId(userId, offset, limit);
    }
}
