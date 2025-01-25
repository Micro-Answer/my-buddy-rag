package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.UpdateOpinionConsumer
import com.example.rag.application.command.producer.UpdateOpinionProducer
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
    fun updateOpinionProducer(queue: BlockingQueue<QnAEvent.UpdateOpinion>): UpdateOpinionProducer =
        UpdateOpinionProducer(queue)

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun updateOpinionConsumer(queue: BlockingQueue<QnAEvent.UpdateOpinion>, qna: QnaSystem): UpdateOpinionConsumer =
        UpdateOpinionConsumer(queue, qna)
}
