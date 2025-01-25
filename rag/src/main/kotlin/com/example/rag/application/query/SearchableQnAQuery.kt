package com.example.rag.application.query

import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.search.SearchSystem

class SearchableQnAQuery(
    private val qna: QnaSystem,
    private val search: SearchSystem,
) {
    init {
        println("create SearchableQnA")
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
