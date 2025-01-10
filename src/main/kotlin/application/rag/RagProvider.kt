package com.example.rag.application.rag

import application.explanation.ExplainerProvider
import application.qna.QnaProvider
import application.search.SearchToolProvider
import core.rag.RagSystem

object RagProvider {
    val rag: RagSystem = RAG(
        QnaProvider.getQna(),
        ExplainerProvider.getExplainer(),
        SearchToolProvider.getSearchTool()
    )

    init {
        println("RAG 생성")
    }
}

//public class RagProvider {
//    private static final RagSystem rag;
//
//    static {
//        rag = new RAG(
//                QnaProvider.getQna(),
//                ExplainerProvider.getExplainer(),
//                SearchToolProvider.getSearchTool()
//
//        );
//        System.out.println("RAG 생성");
//    }
//
//    public static RagSystem getRag() {
//        return rag;
//    }
//}
