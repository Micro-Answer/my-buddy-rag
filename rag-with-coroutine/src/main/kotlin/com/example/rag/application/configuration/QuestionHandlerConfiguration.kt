package com.example.rag.application.configuration

import com.example.rag.application.command.handler.Handler
import com.example.util.ThroughputMonitor
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.rag.event.handler.QnAEventHandler
import core.search.SearchSystem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuestionHandlerConfiguration(private val configuration: CoroutineConfiguration) {
    private val scope: CoroutineScope = configuration.applicationScope()

    private val enrollQuestionMonitor = ThroughputMonitor("Enroll_question_with_coroutine")
    private val updateQuestionMonitor = ThroughputMonitor("Update_question_with_coroutine")
    private val deleteQuestionMonitor = ThroughputMonitor("Delete_question_with_coroutine")

    @Bean
    fun enrollQuestion(qna: QnaSystem, search: SearchSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.EnrollQuestion) {
                scope.launch {
                    val questionId = qna.enrollQuestion(event).questionId
                    search.enrollQuestion(questionId!!, event)
                    enrollQuestionMonitor.increment()
                }
            }
        }

    @Bean
    fun updateQuestion(qna: QnaSystem, search: SearchSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.UpdateQuestion) {
                scope.launch { qna.updateQuestion(event) }
                scope.launch { search.updateQuestion(event) }
                updateQuestionMonitor.increment()
            }
        }

    @Bean
    fun deleteQuestion(qna: QnaSystem, search: SearchSystem): QnAEventHandler =
        Handler { event ->
            if (event is QnAEvent.DeleteQuestion) {
                scope.launch { qna.deleteQuestion(event) }
                scope.launch { search.deleteQuestion(event) }
                deleteQuestionMonitor.increment()
            }
        }

    init {
        enrollQuestionMonitor.startMonitoring()
        updateQuestionMonitor.startMonitoring()
        deleteQuestionMonitor.startMonitoring()
    }
}