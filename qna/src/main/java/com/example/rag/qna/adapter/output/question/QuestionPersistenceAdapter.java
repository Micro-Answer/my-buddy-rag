package com.example.rag.qna.adapter.output.question;

import com.example.rag.qna.domain.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class QuestionPersistenceAdapter implements QuestionPersistencePort {

    private final QuestionRepository questionRepository;

    @Override
    public void saveQuestion(Question q) {
        questionRepository.save(
                new QuestionEntity(q.getCategory(), q.getTitle(), q.getContent(), q.getUserId(), LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public void updateQuestion(Question question) {
        QuestionEntity entity = getQuestionEntity(question.getQuestionId());
        entity.setCategory(question.getCategory());
        entity.setTitle(question.getTitle());
        entity.setContent(question.getContent());
        entity.setUpdatedAt(question.getUpdatedAt());
    }

    @Override
    public void deleteQuestion(String questionId) {
        questionRepository.deleteById(getQuestionIdForMySQL(questionId));
    }

    @Override
    public Question findQuestionById(String questionId) {
        return toQuestion(getQuestionEntity(questionId));
    }

    @Override
    public List<Question> findQuestionsByUserId(String userId, int offset, int limit) {
        return questionRepository.findByUserId(userId, offset, limit)
                .stream()
                .map(QuestionPersistenceAdapter::toQuestion)
                .toList();
    }

    private QuestionEntity getQuestionEntity(String questionId) {
        return questionRepository.findById(getQuestionIdForMySQL(questionId)).orElseThrow();
    }

    private static Question toQuestion(QuestionEntity entity) {
        return new Question(
                getQuestionIdForTransparency(entity.getId()),
                entity.getCategory(),
                entity.getTitle(),
                entity.getContent(),
                entity.getUserId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    private static Long getQuestionIdForMySQL(String questionId) {
        return questionId == null ? null : Long.parseLong(questionId);
    }

    private static String getQuestionIdForTransparency(Long questionId) {
        return questionId + "";
    }
}
