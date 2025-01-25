package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.DeleteOpinionConsumer
import com.example.rag.application.command.producer.DeleteOpinionProducer
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
    fun deleteOpinionProducer(queue: BlockingQueue<QnAEvent.DeleteOpinion>): DeleteOpinionProducer =
        DeleteOpinionProducer(queue)

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun deleteOpinionConsumer(queue: BlockingQueue<QnAEvent.DeleteOpinion>, qna: QnaSystem): DeleteOpinionConsumer =
        DeleteOpinionConsumer(queue, qna)
}