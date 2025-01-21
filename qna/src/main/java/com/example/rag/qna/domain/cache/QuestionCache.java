package com.example.rag.qna.domain.cache;

import core.rag.Question;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class QuestionCache {
    private final Map<String, Question> map;
    private final int maxSize;

    public QuestionCache() {
        this.maxSize = 1000;
        this.map = new LinkedHashMap<>(1000, 0.75f, true);
    }

    public Question getRecentQuestion(String questionId) {
        return map.get(questionId);
    }

    //  FIFO 구조
    public void putQuestion(String questionId, Question question) {
        // 비효율 - 개선 사항
        if (map.size() >= maxSize) {
            String oldestKey = map.keySet().iterator().next();
            map.remove(oldestKey);
        }
        map.put(questionId, question);
    }
}