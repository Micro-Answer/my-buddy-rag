package com.example.rag.application

import core.explanation.ExplainerSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.rag.RagSystem
import core.rag.event.QnAEvent

class Rag(private val searchableQnA: SearchableQnA, private val explainer: ExplainerSystem): RagSystem {
    init {
        println("create Rag")
    }

    override fun execute(event: QnAEvent) {
        searchableQnA.execute(event)
    }

    override fun readQuestion(questionId: String): Question =
        searchableQnA.readQuestion(questionId)

    override fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> =
        searchableQnA.readQuestionTitles(category, offset, limit)

    override fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        searchableQnA.readOpinions(questionId, offset, limit)

    override fun search(query: String, age: Int, gender: String?, personalData: String?): String =
        searchableQnA.search(query).let {
            explainer.explain(it, "나이: %d, 성별: %s, 개인정보: %s".format(age, gender, personalData))
        }
}
