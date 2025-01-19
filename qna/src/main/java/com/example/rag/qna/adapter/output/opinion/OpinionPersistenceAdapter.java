package com.example.rag.qna.adapter.output.opinion;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OpinionPersistenceAdapter implements OpinionPersistencePort {
    private final OpinionRepository opinionRepository;

    @Override
    public void saveOpinion(OpinionEntity opinionEntity) {
        opinionRepository.save(opinionEntity);
    }

    @Override
    public void updateOpinion(OpinionEntity opinionEntity) {
        opinionRepository.save(opinionEntity);
    }

    @Override
    public void deleteOpinion(String opinionId) {
        opinionRepository.deleteById(Long.parseLong(opinionId));
    }

    @Override
    public Optional<OpinionEntity> findOpinionById(String opinionId) {
        return opinionRepository.findById(Long.parseLong(opinionId));
    }

    @Override
    public List<OpinionEntity> findOpinionsByQuestionId(String questionId, int offset, int limit) {
        return opinionRepository.findByQuestionId(questionId, offset, limit);
    }
}