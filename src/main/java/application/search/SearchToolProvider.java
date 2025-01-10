package application.search;

import core.search.SearchSystem;

public class SearchToolProvider {
    private static final SearchSystem searchTool;

    static {
        searchTool = new SearchTool();
        System.out.println("검색기 생성");
    }

    public static SearchSystem getSearchTool() {
        return searchTool;
    }
}
