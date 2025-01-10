package com.example.rag.application.rag

import core.rag.RagSystem
import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.search.SearchSystem

class RAG(qna: QnaSystem, explainer: ExplainerSystem, searchTool: SearchSystem) : RagSystem(qna, explainer, searchTool) {
    override fun enrollQuestion(userId: String, title: String, category: String?, contents: String): String {
        TODO("Not yet implemented")
    }

    override fun updateQuestion(userId: String, questionId: String, category: String?, contents: String?): String {
        TODO("Not yet implemented")
    }

    override fun deleteQuestion(userId: String, questionId: String): String {
        TODO("Not yet implemented")
    }

    override fun readQuestion(questionId: String): String {
        TODO("Not yet implemented")
    }

    override fun readQuestionTitles(category: String?, startNum: Int, endNum: Int): String {
        TODO("Not yet implemented")
    }

    override fun enrollOpinion(userId: String, questionId: String, title: String, contents: String): String {
        TODO("Not yet implemented")
    }

    override fun updateOpinion(userId: String, opinionId: String, title: String?, contents: String?): String {
        TODO("Not yet implemented")
    }

    override fun deleteOpinion(userId: String, opinionId: String): String {
        TODO("Not yet implemented")
    }

    override fun readOpinions(questionId: String, startNum: Int, endNum: Int): String {
        TODO("Not yet implemented")
    }

    override fun search(contents: String, age: Int, gender: String?, personalData: String?): String {
        TODO("Not yet implemented")
    }
}

//class RAG extends RagSystem {
//    RAG(QnaSystem qna, ExplainerSystem explainer, SearchSystem searchTool) {
//        super(qna, explainer, searchTool);
//    }
//}