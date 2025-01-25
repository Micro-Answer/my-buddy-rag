package com.example.rag.application

import com.example.rag.application.event.listener.Listener
import com.example.rag.application.query.SearchableQnAQuery
import core.explanation.ExplainerSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.rag.RagSystem
import core.rag.event.QnAEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class Rag(
    private val qnaQuery: SearchableQnAQuery,
    private val explainer: ExplainerSystem,
    private val listener: Listener
): RagSystem {
    init {
        println("create Rag")
    }

    @Async
    override fun execute(event: QnAEvent) {
        listener.execute(event)
    }

    override fun readQuestion(questionId: String): Question =
        qnaQuery.readQuestion(questionId)

    override fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> =
        qnaQuery.readQuestionTitles(category, offset, limit)

    override fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        qnaQuery.readOpinions(questionId, offset, limit)

    override fun search(query: String, age: Int, gender: String?, personalData: String?): String =
        qnaQuery.search(query).let {
            explainer.explain(it, "나이: %d, 성별: %s, 개인정보: %s".format(age, gender, personalData))
        }
}
