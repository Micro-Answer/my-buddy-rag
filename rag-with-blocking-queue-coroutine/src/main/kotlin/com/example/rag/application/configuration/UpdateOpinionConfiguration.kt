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
class UpdateOpinionConfiguration {
    @Bean
    fun updateOpinionQueue(): BlockingQueue<QnAEvent.UpdateOpinion>
            = ArrayBlockingQueue(1000)

    @Bean
    fun updateOpinionProducer(queue: BlockingQueue<QnAEvent.UpdateOpinion>): Producer<QnAEvent.UpdateOpinion> =
        Producer(queue) { event ->
            if (event is QnAEvent.UpdateOpinion) {
                queue.put(event)
            }
        }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun updateOpinionConsumer(queue: BlockingQueue<QnAEvent.UpdateOpinion>, qna: QnaSystem): Consumer =
        Consumer {
            val event = queue.take()
            qna.updateOpinion(event)
        }
}
