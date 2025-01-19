package com.example.rag.qna.domain.cache;

import core.rag.QuestionDTO;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class QuestionCache {
    private final Map<String, QuestionDTO> map;
    private final int maxSize;

    public QuestionCache(int maxSize) {
        this.maxSize = maxSize;
        this.map = new LinkedHashMap<>(1000, 0.75f, true);
    }

    public QuestionDTO getRecentQuestion(String questionId) {
        return map.get(questionId);
    }

    //  FIFO 구조
    public void putQuestion(String questionId, QuestionDTO questionDTO) {
        // 비효율 - 개선 사항
        if (map.size() >= maxSize) {
            String oldestKey = map.keySet().iterator().next();
            map.remove(oldestKey);
        }
        map.put(questionId, questionDTO);
    }
}