package com.example.rag.application.configuration

import com.example.rag.application.command.handler.Handler
import com.example.rag.application.util.runAsync
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuestionHandlerConfiguration {
    @Bean
    fun enrollQuestion(qna: QnaSystem, search: SearchSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.EnrollQuestion) {
                runAsync {
                    val questionId = qna.enrollQuestion(event).questionId
                    search.enrollQuestion(questionId!!, event)
                }
            }
        }

    @Bean
    fun updateQuestion(qna: QnaSystem, search: SearchSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.UpdateQuestion) {
                runAsync { qna.updateQuestion(event) }
                runAsync { search.updateQuestion(event) }
            }
        }

    @Bean
    fun deleteQuestion(qna: QnaSystem, search: SearchSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.DeleteQuestion) {
                runAsync { qna.deleteQuestion(event) }
                runAsync { search.deleteQuestion(event) }
            }
        }
}