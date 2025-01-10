package core.rag;

import core.explanation.ExplainerSystem;
import core.qna.QnaSystem;
import core.search.SearchSystem;

public abstract class RagSystem {
    private final QnaSystem qna;
    private final ExplainerSystem explainer;
    private final SearchSystem search;

    public RagSystem(QnaSystem qna, ExplainerSystem explainer, SearchSystem search) {
        this.qna = qna;
        this.explainer = explainer;
        this.search = search;
    }
}
