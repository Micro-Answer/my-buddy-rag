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
class UpdateQuestionConfiguration {
    @Bean
    fun updateQuestionQueue(): BlockingQueue<QnAEvent.UpdateQuestion> =
        ArrayBlockingQueue(1000)

    @Bean
    fun updateQuestionProducer(queue: BlockingQueue<QnAEvent.UpdateQuestion>): Producer<QnAEvent.UpdateQuestion> {
        return Producer(queue) { event ->
            if (event is QnAEvent.UpdateQuestion) {
                queue.put(event)
            }
        }
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun updateQuestionConsumer(
        queue: BlockingQueue<QnAEvent.UpdateQuestion>,
        qna: QnaSystem,
        search: SearchSystem
    ): Consumer<QnAEvent.UpdateQuestion> {
        val nThreads = 4
        return Consumer(queue, Executors.newFixedThreadPool(nThreads), nThreads) { event ->
            qna.updateQuestion(event)
            search.updateQuestion(event)
        }
    }
}
