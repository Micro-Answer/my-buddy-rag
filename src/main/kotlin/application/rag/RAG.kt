package com.example.rag.application.rag

import core.rag.RagSystem
import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.rag.OpinionDTO
import core.rag.QuestionDTO
import core.rag.QuestionTitleDTO
import core.search.SearchSystem

class RAG(qna: QnaSystem, explainer: ExplainerSystem, searchTool: SearchSystem) : RagSystem(qna, explainer, searchTool) {

    override fun enrollQuestion(userId: String, title: String, category: String?, contents: String): String {
        return qna.enrollQuestion(userId, title, category, contents);
//        TODO("Not yet implemented")
    }

    override fun updateQuestion(userId: String, questionId: String, category: String?, contents: String?): String {
        return qna.updateQuestion(userId, questionId, category, contents)
    }

    override fun deleteQuestion(userId: String, questionId: String): String {
        return qna.deleteQuestion(userId, questionId)
    }

    override fun readQuestion(questionId: String): QuestionDTO {
        return qna.readQuestion(questionId)
    }

    override fun readQuestionTitles(category: String?, startNum: Int, endNum: Int): Array<QuestionTitleDTO> {
        return qna.readQuestionTitles(category, startNum, endNum)
    }

    override fun enrollOpinion(userId: String, questionId: String, title: String, contents: String): String {
        return qna.enrollOpinion(userId, questionId, title, contents)
    }

    override fun updateOpinion(userId: String, opinionId: String, title: String?, contents: String?): String {
        return qna.updateOpinion(userId, opinionId, title, contents)
    }

    override fun deleteOpinion(userId: String, opinionId: String): String {
        return qna.deleteOpinion(userId, opinionId)
    }

    override fun readOpinions(questionId: String, startNum: Int, endNum: Int): Array<OpinionDTO> {
        return qna.readOpinions(questionId, startNum, endNum)
    }

    override fun search(contents: String, age: Int, gender: String?, personalData: String?): String {
        searchTool.search(contents)
        return explainer.explain(contents, personalData)
    }
}

//class RAG extends RagSystem {
//    RAG(QnaSystem qna, ExplainerSystem explainer, SearchSystem searchTool) {
//        super(qna, explainer, searchTool);
//    }
//}