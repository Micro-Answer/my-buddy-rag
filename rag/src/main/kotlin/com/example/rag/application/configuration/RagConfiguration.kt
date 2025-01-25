package com.example.rag.application.configuration

import com.example.rag.application.Rag
import com.example.rag.application.command.handler.*
import com.example.rag.application.query.SearchableQnAQuery
import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.rag.RagSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RagConfiguration {
    @Bean
    fun handlers(qna: QnaSystem, search: SearchSystem): Map<Class<out QnAEvent>, QnAEventHandler> =
        mapOf(
            QnAEvent.EnrollQuestion::class.java to EnrollQuestionHandler(qna, search),
            QnAEvent.UpdateQuestion::class.java to UpdateQuestionHandler(qna, search),
            QnAEvent.DeleteQuestion::class.java to DeleteQuestionHandler(qna),
            QnAEvent.EnrollOpinion::class.java to EnrollOpinionHandler(qna),
            QnAEvent.UpdateOpinion::class.java to UpdateOpinionHandler(qna),
            QnAEvent.DeleteOpinion::class.java to DeleteOpinionHandler(qna)
        )

    @Bean
    fun searchableQnA(qna: QnaSystem, search: SearchSystem): SearchableQnAQuery =
        SearchableQnAQuery(qna, search)

    @Bean
    fun ragSystem(
        searchableQnAQuery: SearchableQnAQuery,
        explainer: ExplainerSystem,
        handlers: Map<Class<out QnAEvent>, QnAEventHandler>
    ): RagSystem =
        Rag(searchableQnAQuery, explainer, handlers)
}