package com.example.rag.qna.domain

import com.example.rag.qna.adapter.output.opinion.OpinionPersistencePort
import com.example.rag.qna.adapter.output.question.QuestionPersistencePort
import core.qna.QnaSystem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QnAConfiguration {
    @Bean
    fun qna(questionPersistence: QuestionPersistencePort, opinionPersistence: OpinionPersistencePort): QnaSystem =
        Qna(questionPersistence, opinionPersistence)
}