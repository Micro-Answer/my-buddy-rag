package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.Consumer
import com.example.rag.application.command.producer.*
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors

@Configuration
class EnrollOpinionConfiguration {
    @Bean
    fun enrollOpinionQueue(): BlockingQueue<QnAEvent.EnrollOpinion> =
        ArrayBlockingQueue(1000)

    @Bean
    fun enrollOpinionProducer(queue: BlockingQueue<QnAEvent.EnrollOpinion>): Producer<QnAEvent.EnrollOpinion> {
        return Producer(queue) { event ->
            if (event is QnAEvent.EnrollOpinion) {
                queue.put(event)
            }
        }
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun enrollOpinionConsumer(
        queue: BlockingQueue<QnAEvent.EnrollOpinion>,
        qna: QnaSystem
    ): Consumer<QnAEvent.EnrollOpinion> {
        val nThreads = 4
        return Consumer(queue, Executors.newFixedThreadPool(nThreads), nThreads) { event ->
            qna.enrollOpinion(event)
        }
    }
}
