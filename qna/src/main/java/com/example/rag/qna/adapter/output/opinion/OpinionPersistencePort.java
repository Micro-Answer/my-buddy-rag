package com.example.rag.qna.adapter.output.opinion;

import com.example.rag.qna.domain.Opinion;

import java.util.List;

public interface OpinionPersistencePort {
    void saveOpinion(Opinion opinion);
    void updateOpinion(Opinion opinion);
    void deleteOpinion(String opinionId); // MongoDB 식별자의 문자열 타입 고려
    Opinion findOpinionById(String opinionId); // MongoDB 식별자의 문자열 타입 고려
    List<Opinion> findOpinionsByQuestionId(String questionId, int offset, int limit);
}
