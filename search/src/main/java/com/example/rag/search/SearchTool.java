package com.example.rag.search;

import core.search.SearchSystem;

class SearchTool implements SearchSystem {

    @Override
    public String search(String title, String category, String contents) {
        System.out.printf("제목 : %s, 카테고리 : %s, 내용 : %s . 검색했습니다\n", title, category, contents);
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
