package com.example.rag.application.configuration

import com.example.rag.application.command.handler.Handler
import com.example.rag.application.util.runAsync
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpinionHandlerConfiguration {
    @Bean
    fun enrollOpinion(qna: QnaSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.EnrollOpinion) {
                runAsync { qna.enrollOpinion(event) }
            }
        }

    @Bean
    fun updateOpinion(qna: QnaSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.UpdateOpinion) {
                runAsync { qna.updateOpinion(event) }
            }
        }

    @Bean
    fun deleteOpinion(qna: QnaSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.DeleteOpinion) {
                runAsync { qna.deleteOpinion(event) }
            }
        }
}

