package com.example.rag.application.configuration

import com.example.rag.application.Rag
import com.example.rag.application.command.producer.*
import com.example.rag.application.query.SearchableQnAQuery
import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.rag.RagSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Configuration
class RagConfiguration {
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun searchableQnA(qna: QnaSystem, search: SearchSystem): SearchableQnAQuery =
        SearchableQnAQuery(qna, search)

    @Bean
    fun ragSystem(
        searchableQnAQuery: SearchableQnAQuery,
        explainer: ExplainerSystem,
        enrollQuestionProducer: EnrollQuestionProducer,
        updateQuestionProducer: UpdateQuestionProducer,
        deleteQuestionProducer: DeleteQuestionProducer,
        enrollOpinionProducer: EnrollOpinionProducer,
        updateOpinionProducer: UpdateOpinionProducer,
        deleteOpinionProducer: DeleteOpinionProducer
    ): RagSystem {
        val producers: Map<Class<out QnAEvent>, QnAEventHandler> = hashMapOf(
            QnAEvent.EnrollQuestion::class.java to enrollQuestionProducer,
            QnAEvent.UpdateQuestion::class.java to updateQuestionProducer,
            QnAEvent.DeleteQuestion::class.java to deleteQuestionProducer,
            QnAEvent.EnrollOpinion::class.java to enrollOpinionProducer,
            QnAEvent.UpdateOpinion::class.java to updateOpinionProducer,
            QnAEvent.DeleteOpinion::class.java to deleteOpinionProducer
        )
        return Rag(producers, searchableQnAQuery, explainer)
    }

}