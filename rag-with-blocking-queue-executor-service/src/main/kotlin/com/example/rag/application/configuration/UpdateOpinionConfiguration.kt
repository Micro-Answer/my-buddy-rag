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
class UpdateOpinionConfiguration {
    @Bean
    fun updateOpinionQueue(): BlockingQueue<QnAEvent.UpdateOpinion> =
        ArrayBlockingQueue(1000)

    @Bean
    fun updateOpinionProducer(queue: BlockingQueue<QnAEvent.UpdateOpinion>): Producer<QnAEvent.UpdateOpinion> {
        return Producer(queue) { event ->
            if (event is QnAEvent.UpdateOpinion) {
                queue.put(event)
            }
        }
    }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun updateOpinionConsumer(
        queue: BlockingQueue<QnAEvent.UpdateOpinion>,
        qna: QnaSystem
    ): Consumer<QnAEvent.UpdateOpinion> {
        val nThreads = 4
        return Consumer(queue, Executors.newFixedThreadPool(nThreads), nThreads) { event ->
            qna.updateOpinion(event)
        }
    }
}
