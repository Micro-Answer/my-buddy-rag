package com.example.rag.application.configuration

import com.example.rag.application.command.handler.QuestionHandler
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
        QuestionHandler(qna, search) { event ->
            if (event is QnAEvent.EnrollQuestion) {
                qna.enrollQuestion(event)
            }
        }

    @Bean
    fun updateQuestion(qna: QnaSystem, search: SearchSystem): QnAEventHandler =
        QuestionHandler(qna, search) { event ->
            if (event is QnAEvent.UpdateQuestion) {
                qna.updateQuestion(event)
            }
        }

    @Bean
    fun deleteQuestion(qna: QnaSystem, search: SearchSystem): QnAEventHandler =
        QuestionHandler(qna, search) { event ->
            if (event is QnAEvent.DeleteQuestion) {
                qna.deleteQuestion(event)
            }
        }
}