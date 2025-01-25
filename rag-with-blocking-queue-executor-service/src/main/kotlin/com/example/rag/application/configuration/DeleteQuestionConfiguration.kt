package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.Consumer
import com.example.rag.application.command.producer.Producer
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors

@Configuration
class DeleteQuestionConfiguration {
    @Bean
    fun deleteQuestionQueue(): BlockingQueue<QnAEvent.DeleteQuestion> =
        ArrayBlockingQueue(1000)

    @Bean
    fun deleteQuestionProducer(queue: BlockingQueue<QnAEvent.DeleteQuestion>): Producer<QnAEvent.DeleteQuestion> {
        return Producer(queue) { event ->
            if (event is QnAEvent.DeleteQuestion) {
                queue.put(event)
            }
        }
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun deleteQuestionConsumer(
        queue: BlockingQueue<QnAEvent.DeleteQuestion>,
        qna: QnaSystem,
        search: SearchSystem
    ): Consumer<QnAEvent.DeleteQuestion> {
        val nThreads = 4
        return Consumer(queue, Executors.newFixedThreadPool(nThreads), nThreads) { event ->
            qna.deleteQuestion(event)
            search.deleteQuestion(event)
        }
    }
}