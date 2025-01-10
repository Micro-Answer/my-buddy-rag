package application.search;

import core.search.SearchSystem;

class SearchTool implements SearchSystem {

    @Override
    public String search(String contents) {
        String response = contents + "에 대해 검색했습니다";
        System.out.println(response);
        return response;
    }
}
