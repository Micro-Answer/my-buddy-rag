package core.rag;

import core.explanation.ExplainerSystem;
import core.qna.QnaSystem;
import core.search.SearchSystem;

public abstract class RagSystem {
    private final QnaSystem qna;
    private final ExplainerSystem explainer;
    private final SearchSystem searchTool;

    public RagSystem(QnaSystem qna, ExplainerSystem explainer, SearchSystem searchTool) {
        this.qna = qna;
        this.explainer = explainer;
        this.searchTool = searchTool;
    }
}
