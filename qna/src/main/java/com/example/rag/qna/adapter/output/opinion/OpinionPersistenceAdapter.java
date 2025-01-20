package com.example.rag.qna.adapter.output.opinion;

import com.example.rag.qna.domain.Opinion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class OpinionPersistenceAdapter implements OpinionPersistencePort {
    private final OpinionRepository opinionRepository;

    @Override
    public void saveOpinion(Opinion o) {
        opinionRepository.save(
                new OpinionEntity(o.getQuestionId(), o.getTitle(), o.getContent(), o.getUserId(), LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public void updateOpinion(Opinion opinion) {
        OpinionEntity entity = getOpinionEntity(opinion.getOpinionId());
        entity.setTitle(opinion.getTitle());
        entity.setContent(opinion.getContent());
        entity.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void deleteOpinion(String opinionId) {
        opinionRepository.deleteById(getOpinionIdForMySQL(opinionId));
    }

    @Override
    public Opinion findOpinionById(String opinionId) {
        return toOpinion(getOpinionEntity(opinionId));
    }

    @Override
    public List<Opinion> findOpinionsByQuestionId(String questionId, int offset, int limit) {
        return opinionRepository.findByQuestionId(questionId, offset, limit).stream()
                .map(OpinionPersistenceAdapter::toOpinion)
                .toList();
    }

    private OpinionEntity getOpinionEntity(String opinionId) {
        return opinionRepository.findById(getOpinionIdForMySQL(opinionId)).orElseThrow();
    }

    private static Long getOpinionIdForMySQL(String opinionId) {
        return opinionId == null ? null : Long.parseLong(opinionId);
    }

    private static Opinion toOpinion(OpinionEntity entity) {
        return new Opinion(
                getOpinionIdForTransparency(entity.getId()),
                entity.getQuestionId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getUserId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    private static String getOpinionIdForTransparency(Long opinionId) {
        return String.valueOf(opinionId);
    }
}