package com.example.rag.application.configuration

import com.example.rag.application.command.consumer.*
import com.example.rag.application.command.producer.*
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors

@Configuration
class UpdateQuestionConfiguration {
    @Bean
    fun updateQuestionQueue(): BlockingQueue<QnAEvent.UpdateQuestion> =
        ArrayBlockingQueue(1000)

    @Bean
    fun updateQuestionProducer(queue: BlockingQueue<QnAEvent.UpdateQuestion>): UpdateQuestionProducer =
        UpdateQuestionProducer(queue)

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun updateQuestionConsumer(queue: BlockingQueue<QnAEvent.UpdateQuestion>, qna: QnaSystem, search: SearchSystem): UpdateQuestionConsumer {
        val nThreads = 4
        return UpdateQuestionConsumer(queue, Executors.newFixedThreadPool(nThreads), nThreads, qna, search)
    }
}
