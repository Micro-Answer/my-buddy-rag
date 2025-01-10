package application.rag;

import core.explanation.ExplainerSystem;
import core.qna.QnaSystem;
import core.rag.RagSystem;
import core.search.SearchSystem;

public class RAG extends RagSystem {
    public RAG(QnaSystem qna, ExplainerSystem explainer, SearchSystem search) {
        super(qna, explainer, search);
    }
}
