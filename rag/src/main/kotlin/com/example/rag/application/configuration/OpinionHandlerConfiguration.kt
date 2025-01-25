package com.example.rag.application.configuration

import com.example.rag.application.command.handler.OpinionHandler
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpinionHandlerConfiguration {
    @Bean
    fun enrollOpinion(qna: QnaSystem): QnAEventHandler
            = OpinionHandler(qna) { event ->
        if (event is QnAEvent.EnrollOpinion) {
            qna.enrollOpinion(event)
        }
    }

    @Bean
    fun updateOpinion(qna: QnaSystem): QnAEventHandler =
        OpinionHandler(qna) { event ->
            if (event is QnAEvent.UpdateOpinion) {
                qna.updateOpinion(event)
            }
        }

    @Bean
    fun deleteOpinion(qna: QnaSystem): QnAEventHandler =
        OpinionHandler(qna) { event ->
            if (event is QnAEvent.DeleteOpinion) {
                qna.deleteOpinion(event)
            }
        }
}