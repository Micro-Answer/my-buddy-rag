package com.example.rag.qna.domain.cache;

import core.rag.QuestionTitleDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CategoryQuestionTitleCache {
    private final Map<String, QuestionTitleCache> caches;

    CategoryQuestionTitleCache() {
        caches = new HashMap<>();
    }

    public QuestionTitleDTO[] getRecentQuestionTitles(String category, int startNum, int endNum) {
        caches.computeIfAbsent(category, k -> new QuestionTitleCache(1000));
        return caches.get(category).getRecentQuestionTitles(startNum, endNum);
    }

    public void putRecentQuestionTitle(String category, QuestionTitleDTO questionTitle) {
        caches.computeIfAbsent(category, k -> new QuestionTitleCache(1000));
        caches.get(category).putRecentQuestionTitle(questionTitle);
    }
}
