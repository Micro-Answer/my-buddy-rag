package com.example.rag.application

import com.example.rag.application.event.handler.*
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
    fun searchableQnA(qna: QnaSystem, search: SearchSystem): SearchableQnA {
        val handlers = mapOf(
            QnAEvent.EnrollQuestion::class.java to EnrollQuestionHandler(qna, search),
            QnAEvent.UpdateQuestion::class.java to UpdateQuestionHandler(qna, search),
            QnAEvent.DeleteQuestion::class.java to DeleteQuestionHandler(qna),
            QnAEvent.EnrollOpinion::class.java to EnrollOpinionHandler(qna),
            QnAEvent.UpdateOpinion::class.java to UpdateOpinionHandler(qna),
            QnAEvent.DeleteOpinion::class.java to DeleteOpinionHandler(qna)
        )
        return SearchableQnA(qna, search,handlers)
    }

    @Bean
    fun ragSystem(searchableQnA: SearchableQnA, explainer: ExplainerSystem): RagSystem =
        Rag(searchableQnA, explainer)
}