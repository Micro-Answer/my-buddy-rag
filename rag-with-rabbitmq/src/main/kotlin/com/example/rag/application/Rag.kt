package com.example.rag.application

import com.example.rag.application.message.publisher.Publisher
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
    private val publisher: Publisher
): RagSystem {
    init {
        println("create Rag")
    }

    @Async
    override fun execute(event: QnAEvent) {
        when (event) {
            is QnAEvent.EnrollQuestion -> publisher.enrollQuestion(event)
            is QnAEvent.UpdateQuestion -> publisher.updateQuestion(event)
            is QnAEvent.DeleteQuestion -> publisher.deleteQuestion(event)
            is QnAEvent.EnrollOpinion -> publisher.enrollOpinion(event)
            is QnAEvent.UpdateOpinion -> publisher.updateOpinion(event)
            is QnAEvent.DeleteOpinion -> publisher.deleteOpinion(event)
            QnAEvent.Shutdown -> {}
        }
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
