package com.example.rag.qna.adapter.output.opinion;

import java.util.List;
import java.util.Optional;

public interface OpinionPersistencePort {
    void saveOpinion(OpinionEntity opinionEntity);
    void updateOpinion(OpinionEntity opinionEntity);
    void deleteOpinion(String opinionId); // MongoDB 식별자의 문자열 타입 고려
    Optional<OpinionEntity> findOpinionById(String opinionId); // MongoDB 식별자의 문자열 타입 고려
    List<OpinionEntity> findOpinionsByQuestionId(String questionId, int offset, int limit);
}
