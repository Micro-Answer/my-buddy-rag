package com.example.rag.application

import core.rag.OpinionDTO
import core.rag.QuestionDTO
import core.rag.QuestionTitleDTO
import core.rag.RagSystem
import org.springframework.stereotype.Component

@Component
class Rag(private var searchableQnA: SearchableQnA): RagSystem {
    init {
        println("create Rag")
    }

    override fun enrollQuestion(userId: String?, title: String?, category: String?, contents: String?): String {
        return "enrollQuestion"
    }

    override fun updateQuestion(userId: String?, questionId: String?, category: String?, contents: String?): String {
        return "updateQuestion"
    }

    override fun deleteQuestion(userId: String?, questionId: String?): String {
        return "deleteQuestion"
    }

    override fun readQuestion(questionId: String?): QuestionDTO {
        return object: QuestionDTO {
            override fun userId(): String { return "userId" }
            override fun questionId(): String { return "questionId" }
            override fun title(): String { return "questionId" }
            override fun category(): String { return "category" }
            override fun contents(): String { return "contents" }
            override fun createdDate(): String { return "createdDate" }
        }
    }

    override fun readQuestionTitles(category: String?, startNum: Int, endNum: Int): Array<QuestionTitleDTO> {
        return arrayOf()
    }

    override fun enrollOpinion(userId: String?, questionId: String?, title: String?, contents: String?): String {
        return "enrollOpinion"
    }

    override fun updateOpinion(userId: String?, opinionId: String?, title: String?, contents: String?): String {
        return "updateOpinion"
    }

    override fun deleteOpinion(userId: String?, opinionId: String?): String {
        return "deleteOpinion"
    }

    override fun readOpinions(questionId: String?, startNum: Int, endNum: Int): Array<OpinionDTO> {
        return arrayOf()
    }

    override fun search(contents: String?, age: Int, gender: String?, personalData: String?): String {
        return "search"
    }
}
