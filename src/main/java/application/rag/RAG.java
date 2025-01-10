package application.rag;

import core.explanation.ExplainerSystem;
import core.qna.QnaSystem;
import core.rag.RagSystem;
import core.search.SearchSystem;

class RAG extends RagSystem {
    RAG(QnaSystem qna, ExplainerSystem explainer, SearchSystem searchTool) {
        super(qna, explainer, searchTool);
    }
}
