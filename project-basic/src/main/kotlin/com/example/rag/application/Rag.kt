package com.example.rag.application

import core.rag.OpinionDTO
import core.rag.QuestionDTO
import core.rag.QuestionTitleDTO
import core.rag.RagSystem
import org.springframework.stereotype.Component

@Component
class Rag(private val searchableQnA: SearchableQnA): RagSystem(null, null, null) {
    init {
        println("create Rag")
    }

    override fun enrollQuestion(userId: String?, title: String?, category: String?, contents: String?): String {
        TODO("Not yet implemented")
    }

    override fun updateQuestion(userId: String?, questionId: String?, category: String?, contents: String?): String {
        TODO("Not yet implemented")
    }

    override fun deleteQuestion(userId: String?, questionId: String?): String {
        TODO("Not yet implemented")
    }

    override fun readQuestion(questionId: String?): QuestionDTO {
        TODO("Not yet implemented")
    }

    override fun readQuestionTitles(category: String?, startNum: Int, endNum: Int): Array<QuestionTitleDTO> {
        TODO("Not yet implemented")
    }

    override fun enrollOpinion(userId: String?, questionId: String?, title: String?, contents: String?): String {
        TODO("Not yet implemented")
    }

    override fun updateOpinion(userId: String?, opinionId: String?, title: String?, contents: String?): String {
        TODO("Not yet implemented")
    }

    override fun deleteOpinion(userId: String?, opinionId: String?): String {
        TODO("Not yet implemented")
    }

    override fun readOpinions(questionId: String?, startNum: Int, endNum: Int): Array<OpinionDTO> {
        TODO("Not yet implemented")
    }

    override fun search(contents: String?, age: Int, gender: String?, personalData: String?): String {
        TODO("Not yet implemented")
    }
}
