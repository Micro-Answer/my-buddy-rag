package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.DeleteQuestionConsumer
import com.example.rag.application.command.producer.DeleteQuestionProducer
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

@Configuration
class DeleteQuestionConfiguration {
    @Bean
    fun deleteQuestionQueue(): BlockingQueue<QnAEvent.DeleteQuestion>
            = ArrayBlockingQueue(1000)

    @Bean
    fun deleteQuestionProducer(queue: BlockingQueue<QnAEvent.DeleteQuestion>): DeleteQuestionProducer =
        DeleteQuestionProducer(queue)

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun deleteQuestionConsumer(queue: BlockingQueue<QnAEvent.DeleteQuestion>, qna: QnaSystem, search: SearchSystem): DeleteQuestionConsumer =
        DeleteQuestionConsumer(queue, qna, search)
}