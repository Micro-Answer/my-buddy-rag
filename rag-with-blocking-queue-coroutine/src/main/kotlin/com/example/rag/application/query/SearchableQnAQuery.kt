package com.example.rag.application.query

import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.search.SearchSystem

class SearchableQnAQuery(private val qna: QnaSystem, private val search: SearchSystem) {
    fun readQuestion(questionId: String): Question =
        qna.readQuestion(questionId)

    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> =
        qna.readQuestionTitles(category, offset, limit)

    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        qna.readOpinions(questionId, offset, limit)

    fun search(query: String): String {
        val questionId = search.search(query)
        return qna.readOpinions(questionId, 0, 1).getOrNull(0)?.content ?: "No Content"
    }
}