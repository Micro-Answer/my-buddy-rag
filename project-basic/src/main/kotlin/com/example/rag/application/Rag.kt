package com.example.rag.application

import core.explanation.ExplainerSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.rag.RagSystem
import org.springframework.stereotype.Component

@Component
class Rag(private val searchableQnA: SearchableQnA, private val explainer: ExplainerSystem): RagSystem {
    init {
        println("create Rag")
    }

    override fun enrollQuestion(userId: String, title: String, category: String, content: String): String {
        return searchableQnA.enrollQuestion(userId, title, category, content)
    }

    override fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String): String {
        return searchableQnA.updateQuestion(userId, questionId, title, category, content)
    }

    override fun deleteQuestion(userId: String, questionId: String): String {
        return searchableQnA.deleteQuestion(userId, questionId)
    }

    override fun readQuestion(questionId: String): Question {
        return searchableQnA.readQuestion(questionId)
    }

    override fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> {
        return searchableQnA.readQuestionTitles(category, offset, limit)
    }

    override fun enrollOpinion(userId: String, questionId: String, title: String, content: String): String {
        return searchableQnA.enrollOpinion(userId, questionId, title, content)
    }

    override fun updateOpinion(userId: String, opinionId: String, title: String, content: String): String {
        return searchableQnA.updateOpinion(userId, opinionId, title, content)
    }

    override fun deleteOpinion(userId: String, opinionId: String): String {
        return searchableQnA.deleteOpinion(userId, opinionId)
    }

    override fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> {
        return searchableQnA.readOpinions(questionId, offset, limit)
    }

    override fun search(query: String, age: Int, gender: String?, personalData: String?): String {
        val content = searchableQnA.search(query)
        return explainer.explain(content, "나이: %d, 성별: %s, 개인정보: %s".format(age, gender, personalData))
    }
}
