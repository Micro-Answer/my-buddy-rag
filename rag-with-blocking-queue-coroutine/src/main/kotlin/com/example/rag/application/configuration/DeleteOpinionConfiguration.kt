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
class DeleteOpinionConfiguration {
    @Bean
    fun deleteOpinionQueue(): BlockingQueue<QnAEvent.DeleteOpinion>
            = ArrayBlockingQueue(1000)

    @Bean
    fun deleteOpinionProducer(queue: BlockingQueue<QnAEvent.DeleteOpinion>): Producer<QnAEvent.DeleteOpinion> =
        Producer(queue) { event ->
            if (event is QnAEvent.DeleteOpinion) {
                queue.put(event)
            }
        }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun deleteOpinionConsumer(queue: BlockingQueue<QnAEvent.DeleteOpinion>, qna: QnaSystem): Consumer =
        Consumer {
            val event = queue.take()
            qna.deleteOpinion(event)
        }
}