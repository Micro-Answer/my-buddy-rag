package application.rag;

import core.explanation.ExplainerSystem;
import core.qna.QnaSystem;
import core.rag.RagSystem;
import core.search.SearchSystem;

public record RAG(QnaSystem qna, ExplainerSystem explainer, SearchSystem search) implements RagSystem {
}
