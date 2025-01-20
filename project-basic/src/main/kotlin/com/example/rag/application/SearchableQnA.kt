package com.example.rag.application

import core.qna.QnaSystem
import core.rag.OpinionDTO
import core.rag.QuestionDTO
import core.rag.QuestionTitleDTO
import core.search.SearchSystem
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SearchableQnA(private val qna: QnaSystem, private val search: SearchSystem) {
    init {
        println("create SearchableQnA")
    }

    fun enrollQuestion(userId: String?, title: String?, category: String?, contents: String?): String {
        return qna.enrollQuestion(userId, title, category, contents)
    }

    fun updateQuestion(userId: String?, questionId: String?, category: String?, contents: String?): String {
        return "updateQuestion"
    }

    fun deleteQuestion(userId: String?, questionId: String?): String {
        return "deleteQuestion"
    }

    fun readQuestion(questionId: String?): QuestionDTO {
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

    fun readQuestionTitles(category: String?, startNum: Int, endNum: Int): Array<QuestionTitleDTO> {
        return arrayOf()
    }

    fun enrollOpinion(userId: String?, questionId: String?, title: String?, contents: String?): String {
        return "enrollOpinion"
    }

    fun updateOpinion(userId: String?, opinionId: String?, title: String?, contents: String?): String {
        return "updateOpinion"
    }

    fun deleteOpinion(userId: String?, opinionId: String?): String {
        return "deleteOpinion"
    }

    fun readOpinions(questionId: String?, startNum: Int, endNum: Int): Array<OpinionDTO> {
        return arrayOf()
    }

    fun search(contents: String?, age: Int, gender: String?, personalData: String?): String {
        return "search"
    }
}