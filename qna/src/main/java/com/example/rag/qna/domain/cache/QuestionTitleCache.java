package com.example.rag.qna.domain.cache;

import core.rag.QuestionTitleDTO;

public class QuestionTitleCache {
    private final QuestionTitleDTO[] cache;
    private int first;

    public QuestionTitleCache(int size) {
        cache = new QuestionTitleDTO[size];
        first = 0;
    }

    // 비효율 - 개선 사항
    // synchronized 최적화 필요(성능 비교 필요)
    // 수정 삭제 대비 일정 주기마다 최신화
    public synchronized QuestionTitleDTO[] getRecentQuestionTitles(int startNum, int endNum) {
        QuestionTitleDTO[] response = new QuestionTitleDTO[endNum - startNum + 1];

        for (int i = startNum; i <= endNum; i++) {
            QuestionTitleDTO current = cache[(first + i) % cache.length];
            if (current == null) {
                return response;
            }
            response[i - startNum] = current;
        }

        return response;
    }

    public synchronized void putRecentQuestionTitle(QuestionTitleDTO questionTitle) {
        first = (first + 1) % cache.length;
        cache[first] = questionTitle;
    }
}
