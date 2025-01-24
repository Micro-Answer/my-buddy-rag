package com.example.rag.application

import com.example.rag.application.event.handler.QnAEventHandler
import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.rag.event.QnAEvent
import core.search.SearchSystem


class SearchableQnA(
    private val qna: QnaSystem,
    private val search: SearchSystem,
    private val handlers: Map<Class<out QnAEvent>, QnAEventHandler>
) {
    init {
        println("create SearchableQnA")
    }

    fun execute(event: QnAEvent) {
        handlers[event::class.java]?.handle(event) ?: throw IllegalArgumentException("No handler for event: ${event.javaClass}")
    }

    fun readQuestion(questionId: String): Question =
        qna.readQuestion(questionId)

    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> =
        qna.readQuestionTitles(category, offset, limit)

    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        qna.readOpinions(questionId, offset, limit)

    fun search(query: String): String =
        search.search(query).let {
            qna.readOpinions(it, 0, 1)[0].content
        }
}
