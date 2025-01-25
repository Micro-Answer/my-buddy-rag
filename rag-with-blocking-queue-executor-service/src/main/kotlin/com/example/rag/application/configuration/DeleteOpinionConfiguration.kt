package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.Consumer
import com.example.rag.application.command.producer.Producer
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors

@Configuration
class DeleteOpinionConfiguration {
    @Bean
    fun deleteOpinionQueue(): BlockingQueue<QnAEvent.DeleteOpinion> =
        ArrayBlockingQueue(1000)

    @Bean
    fun deleteOpinionProducer(queue: BlockingQueue<QnAEvent.DeleteOpinion>): Producer<QnAEvent.DeleteOpinion> {
        return Producer(queue) { event ->
            if (event is QnAEvent.DeleteOpinion) {
                queue.put(event)
            }
        }
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun deleteOpinionConsumer(
        queue: BlockingQueue<QnAEvent.DeleteOpinion>,
        qna: QnaSystem
    ): Consumer<QnAEvent.DeleteOpinion> {
        val nThreads = 4
        return Consumer(queue, Executors.newFixedThreadPool(nThreads), nThreads) { event ->
            qna.deleteOpinion(event)
        }
    }

}