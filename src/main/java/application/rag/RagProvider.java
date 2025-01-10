package application.rag;

import application.explanation.ExplainerProvider;
import application.qna.QnaProvider;
import application.search.SearchToolProvider;
import core.rag.RagSystem;

public class RagProvider {
    private static final RagSystem rag;

    static {
        rag = new RAG(
                QnaProvider.getQna(),
                ExplainerProvider.getExplainer(),
                SearchToolProvider.getSearchTool()

        );
        System.out.println("RAG 생성");
    }

    public static RagSystem getRag() {
        return rag;
    }
}
