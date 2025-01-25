package com.example.rag.application.configuration

import com.example.rag.application.command.Consumer
import com.example.rag.application.command.Producer
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

@Configuration
class EnrollOpinionConfiguration {
    @Bean
    fun enrollOpinionQueue(): BlockingQueue<QnAEvent.EnrollOpinion>
            = ArrayBlockingQueue(1000)

    @Bean
    fun enrollOpinionProducer(queue: BlockingQueue<QnAEvent.EnrollOpinion>): Producer<QnAEvent.EnrollOpinion> =
        Producer(queue) { event ->
            if (event is QnAEvent.EnrollOpinion) {
                queue.put(event)
            }
        }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun enrollOpinionConsumer(queue: BlockingQueue<QnAEvent.EnrollOpinion>, qna: QnaSystem): Consumer =
        Consumer {
            val event = queue.take()
            qna.enrollOpinion(event)
        }
}
