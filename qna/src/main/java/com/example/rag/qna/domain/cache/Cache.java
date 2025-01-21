package com.example.rag.qna.domain.cache;

import core.qna.CacheSystem;
import core.rag.Question;
import core.rag.QuestionTitleDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class Cache implements CacheSystem {
    private final CategoryQuestionTitleCache questionTitleCache;
    private final QuestionCache questionCache;

    @Override
    public QuestionTitleDTO[] getRecentQuestionTitles(String category, int startNum, int endNum) {
        return questionTitleCache.getRecentQuestionTitles(category, startNum, endNum);
    }

    @Override
    public void putRecentQuestionTitle(String category, QuestionTitleDTO questionTitle) {
        questionTitleCache.putRecentQuestionTitle(category, questionTitle);
    }

    @Override
    public Question getRecentQuestion(String questionId) {
        return questionCache.getRecentQuestion(questionId);
    }

    @Override
    public void putRecentQuestion(String questionId, Question question) {
        questionCache.putQuestion(questionId, question);
    }
}
