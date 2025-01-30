package com.example.rag.application.configuration

import com.example.rag.application.command.Consumer
import com.example.rag.application.command.Producer
import com.example.util.ThroughputMonitor
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

@Configuration
class EnrollQuestionConfiguration {
    private val enrollQuestionMonitor = ThroughputMonitor("Enroll_question_with_coroutine")

    init {
        enrollQuestionMonitor.startMonitoring()
    }

    @Bean
    fun enrollQuestionQueue(): BlockingQueue<QnAEvent.EnrollQuestion>
            = ArrayBlockingQueue(1000)

    @Bean
    fun enrollQuestionProducer(queue: BlockingQueue<QnAEvent.EnrollQuestion>): Producer<QnAEvent.EnrollQuestion> =
        Producer(queue) { event ->
            if (event is QnAEvent.EnrollQuestion) {
                queue.put(event)
            }
        }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun enrollQuestionConsumer(queue: BlockingQueue<QnAEvent.EnrollQuestion>, qna: QnaSystem, search: SearchSystem): Consumer =
        Consumer {
            val event = queue.take()
            val questionId = qna.enrollQuestion(event).questionId
            search.enrollQuestion(questionId!!, event)
            enrollQuestionMonitor.increment()
        }
}
