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
class UpdateQuestionConfiguration {
    private val updateQuestionMonitor = ThroughputMonitor("Update_question_with_coroutine")

    init {
        updateQuestionMonitor.startMonitoring()
    }
    @Bean
    fun updateQuestionQueue(): BlockingQueue<QnAEvent.UpdateQuestion>
            = ArrayBlockingQueue(1000)

    @Bean
    fun updateQuestionProducer(queue: BlockingQueue<QnAEvent.UpdateQuestion>): Producer<QnAEvent.UpdateQuestion> =
        Producer(queue) { event ->
            if (event is QnAEvent.UpdateQuestion) {
                queue.put(event)
            }
        }

    @Bean(initMethod = "init", destroyMethod = "cleanup")
    fun updateQuestionConsumer(queue: BlockingQueue<QnAEvent.UpdateQuestion>, qna: QnaSystem, search: SearchSystem): Consumer =
        Consumer {
            val event = queue.take()
            qna.updateQuestion(event)
            search.updateQuestion(event)
            updateQuestionMonitor.increment()
        }
}
