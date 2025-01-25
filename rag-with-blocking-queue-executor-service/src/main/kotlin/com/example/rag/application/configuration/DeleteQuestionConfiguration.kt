package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.DeleteOpinionConsumer
import com.example.rag.application.command.consumer.DeleteQuestionConsumer
import com.example.rag.application.command.producer.DeleteQuestionProducer
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors

@Configuration
class DeleteQuestionConfiguration {
    @Bean
    fun deleteQuestionQueue(): BlockingQueue<QnAEvent.DeleteQuestion> =
        ArrayBlockingQueue(1000)

    @Bean
    fun deleteQuestionProducer(queue: BlockingQueue<QnAEvent.DeleteQuestion>): DeleteQuestionProducer =
        DeleteQuestionProducer(queue)

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun deleteQuestionConsumer(queue: BlockingQueue<QnAEvent.DeleteQuestion>, qna: QnaSystem, search: SearchSystem): DeleteQuestionConsumer {
        val nThreads = 4
        return DeleteQuestionConsumer(queue, Executors.newFixedThreadPool(nThreads), nThreads, qna, search)
    }
}