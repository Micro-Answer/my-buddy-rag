package com.example.rag.application.rag

import core.rag.RagSystem
import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.search.SearchSystem

class RAG(qna: QnaSystem, explainer: ExplainerSystem, searchTool: SearchSystem) : RagSystem(qna, explainer, searchTool) {
}

//class RAG extends RagSystem {
//    RAG(QnaSystem qna, ExplainerSystem explainer, SearchSystem searchTool) {
//        super(qna, explainer, searchTool);
//    }
//}