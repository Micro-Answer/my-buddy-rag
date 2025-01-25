package com.example.rag.application

import com.example.rag.application.query.SearchableQnAQuery
import core.explanation.ExplainerSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.rag.RagSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler

fun QnAEventHandler.produce(event: QnAEvent) {
    handle(event)
}

class Rag(
    private val producers: Map<Class<out QnAEvent>, QnAEventHandler>,
    private val searchableQnAQuery: SearchableQnAQuery,
    private val explainer: ExplainerSystem
): RagSystem {
    override fun execute(event: QnAEvent) {
        producers[event::class.java]?.produce(event) ?: throw IllegalArgumentException("No handler for event: ${event.javaClass}")
    }

    override fun readQuestion(questionId: String): Question =
        searchableQnAQuery.readQuestion(questionId)

    override fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> =
        searchableQnAQuery.readQuestionTitles(category, offset, limit)

    override fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        searchableQnAQuery.readOpinions(questionId, offset, limit)

    override fun search(query: String, age: Int, gender: String?, personalData: String?): String =
        searchableQnAQuery.search(query).let {
            explainer.explain(it, "나이: %d, 성별: %s, 개인정보: %s".format(age, gender, personalData))
        }
}
