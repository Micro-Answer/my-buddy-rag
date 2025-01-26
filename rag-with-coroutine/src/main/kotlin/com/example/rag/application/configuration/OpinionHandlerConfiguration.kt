package com.example.rag.application.configuration

import com.example.rag.application.command.handler.Handler
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpinionHandlerConfiguration(private val configuration: CoroutineConfiguration) {
    private val scope: CoroutineScope = configuration.applicationScope()

    @Bean
    fun enrollOpinion(qna: QnaSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.EnrollOpinion) {
                scope.launch { qna.enrollOpinion(event) }
            }
        }

    @Bean
    fun updateOpinion(qna: QnaSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.UpdateOpinion) {
                scope.launch { qna.updateOpinion(event) }
            }
        }

    @Bean
    fun deleteOpinion(qna: QnaSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.DeleteOpinion) {
                scope.launch { qna.deleteOpinion(event) }
            }
        }
}

