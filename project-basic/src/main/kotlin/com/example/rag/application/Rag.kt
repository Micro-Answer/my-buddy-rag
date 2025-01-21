package com.example.rag.application

import core.explanation.ExplainerSystem
import core.rag.OpinionDTO
import core.rag.QuestionDTO
import core.rag.QuestionTitleDTO
import core.rag.RagSystem
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class Rag(private var searchableQnA: SearchableQnA, private var explainer: ExplainerSystem): RagSystem {
    init {
        println("create Rag")
    }

    override fun enrollQuestion(userId: String?, title: String?, category: String?, contents: String?): String {
        return searchableQnA.enrollQuestion(userId, title, category, contents)
    }

    override fun updateQuestion(userId: String?, questionId: String?, category: String?, contents: String?): String {
        return "updateQuestion"
    }

    override fun deleteQuestion(userId: String?, questionId: String?): String {
        return "deleteQuestion"
    }

    override fun readQuestion(questionId: String?): QuestionDTO {
        return object: QuestionDTO {
            override fun getQuestionId(): String { return "questionId" }
            override fun getCategory(): String { return "category" }
            override fun getTitle(): String { return "title" }
            override fun getContent(): String { return "content" }
            override fun getUserId(): String { return "userId" }
            override fun getCreatedAt(): LocalDateTime { return LocalDateTime.now() }
            override fun getUpdatedAt(): LocalDateTime { return LocalDateTime.now() }
        }
    }

    override fun readQuestionTitles(category: String?, startNum: Int, endNum: Int): Array<QuestionTitleDTO?>? {
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

    override fun readOpinions(questionId: String?, startNum: Int, endNum: Int): Array<OpinionDTO?>? {
        return arrayOf()
    }

    override fun search(contents: String?, age: Int, gender: String?, personalData: String?): String {
        return "search"
    }
}
