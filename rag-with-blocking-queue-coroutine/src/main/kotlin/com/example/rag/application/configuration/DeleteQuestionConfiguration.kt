package com.example.rag.application.configuration

import com.example.rag.application.command.Consumer
import com.example.rag.application.command.Producer
import com.example.util.ThroughputMonitor
import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

@Configuration
class DeleteQuestionConfiguration {
    private val deleteQuestionMonitor = ThroughputMonitor("Delete_question_with_coroutine")

    init {
        deleteQuestionMonitor.startMonitoring()
    }
    @Bean
    fun deleteQuestionQueue(): BlockingQueue<QnAEvent.DeleteQuestion>
            = ArrayBlockingQueue(1000)

    @Bean
    fun deleteQuestionProducer(queue: BlockingQueue<QnAEvent.DeleteQuestion>): Producer<QnAEvent.DeleteQuestion> =
        Producer(queue) { event ->
            if (event is QnAEvent.DeleteQuestion) {
                queue.put(event)
            }
        }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun deleteQuestionConsumer(queue: BlockingQueue<QnAEvent.DeleteQuestion>, qna: QnaSystem, search: SearchSystem): Consumer =
        Consumer {
            val event = queue.take()
            qna.deleteQuestion(event)
            search.deleteQuestion(event)
            deleteQuestionMonitor.increment()
        }
}