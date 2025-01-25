package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.EnrollOpinionConsumer
import com.example.rag.application.command.producer.*
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
    fun enrollOpinionProducer(queue: BlockingQueue<QnAEvent.EnrollOpinion>): EnrollOpinionProducer =
        EnrollOpinionProducer(queue)

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun enrollOpinionConsumer(queue: BlockingQueue<QnAEvent.EnrollOpinion>, qna: QnaSystem): EnrollOpinionConsumer =
        EnrollOpinionConsumer(queue, qna)
}
