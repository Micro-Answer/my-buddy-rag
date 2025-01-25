package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.*
import com.example.rag.application.command.producer.*
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors

@Configuration
class EnrollQuestionConfiguration {
    @Bean
    fun enrollQuestionQueue(): BlockingQueue<QnAEvent.EnrollQuestion> =
        ArrayBlockingQueue(1000)

    @Bean
    fun enrollQuestionProducer(queue: BlockingQueue<QnAEvent.EnrollQuestion>): Producer<QnAEvent.EnrollQuestion> {
        return Producer(queue) { event ->
            if (event is QnAEvent.EnrollQuestion) {
                queue.put(event)
            }
        }
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun enrollQuestionConsumer(
        queue: BlockingQueue<QnAEvent.EnrollQuestion>,
        qna: QnaSystem,
        search: SearchSystem
    ): Consumer<QnAEvent.EnrollQuestion> {
        val nThreads = 4
        return Consumer(queue, Executors.newFixedThreadPool(nThreads), nThreads) { event ->
            val questionId = qna.enrollQuestion(event).questionId
            search.enrollQuestion(questionId!!, event)
        }
    }
}
