package com.example.rag.search;

import core.search.SearchSystem;
import org.springframework.stereotype.Component;

@Component
class SearchTool implements SearchSystem {

    @Override
    public String search(String title) {
        System.out.printf("%s 검색했습니다\n", title);
        return "가장 유사한 질문 아이디";
    }

    @Override
    public String search(String query, String category) {
        System.out.printf("검색 문장 : %s, 카테고리 : %s . 검색했습니다\n", query, category);
        return "가장 유사한 질문 아이디";
    }

    @Override
    public String enrollQuestion(String questionId, String title, String category, String contents) {
        return "enrollQuestion";
    }

    @Override
    public String updateQuestion(String questionId, String title, String category, String contents) {
        return "updateQuestion";
    }

    @Override
    public String deleteQuestion(String questionId) {
        return "deleteQuestion";
    }
}
