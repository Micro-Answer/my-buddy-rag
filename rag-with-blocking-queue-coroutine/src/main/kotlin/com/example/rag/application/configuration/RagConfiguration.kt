package com.example.rag.application.configuration

import com.example.rag.application.Rag
import com.example.rag.application.command.producer.*
import com.example.rag.application.query.SearchableQnAQuery
import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.rag.RagSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RagConfiguration {
    @Bean
    fun searchableQnAQuery(qna: QnaSystem, search: SearchSystem): SearchableQnAQuery =
        SearchableQnAQuery(qna, search)

    @Bean
    fun ragSystem(
        qnaQuery: SearchableQnAQuery,
        explainer: ExplainerSystem,
        enrollQuestionProducer: EnrollQuestionProducer,
        updateQuestionProducer: UpdateQuestionProducer,
        deleteQuestionProducer: DeleteQuestionProducer,
        enrollOpinionProducer: EnrollOpinionProducer,
        updateOpinionProducer: UpdateOpinionProducer,
        deleteOpinionProducer: DeleteOpinionProducer
    ): RagSystem {
        val handlers = mapOf(
            QnAEvent.EnrollQuestion::class.java to enrollQuestionProducer,
            QnAEvent.UpdateQuestion::class.java to updateQuestionProducer,
            QnAEvent.DeleteQuestion::class.java to deleteQuestionProducer,
            QnAEvent.EnrollOpinion::class.java to enrollOpinionProducer,
            QnAEvent.UpdateOpinion::class.java to updateOpinionProducer,
            QnAEvent.DeleteOpinion::class.java to deleteOpinionProducer
        )
        return Rag(handlers, qnaQuery, explainer)
    }
}
