package com.example.rag.application.event.listener

import com.example.rag.application.event.events.*
import core.qna.QnaSystem
import core.search.SearchSystem
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class Listener(
    private val qna: QnaSystem,
    private val search: SearchSystem,
) {
    @EventListener
    @Async
    fun enrollQuestion(event: QuestionEnrollEvent) {
        val command = event.command
        qna.enrollQuestion(command).questionId?.let { questionId ->
            search.enrollQuestion(questionId, command)
        }
    }

    @EventListener
    @Async
    fun updateQuestion(event: QuestionUpdateEvent) {
        val command = event.command
        qna.updateQuestion(command)
        search.updateQuestion(command)
    }

    @EventListener
    @Async
    fun deleteQuestion(event: QuestionDeleteEvent) {
        val command = event.command
        qna.deleteQuestion(command)
        search.deleteQuestion(command)
    }

    @EventListener
    @Async
    fun enrollOpinion(event: OpinionEnrollEvent) {
        val command = event.command
        qna.enrollOpinion(command)
    }

    @EventListener
    @Async
    fun updateOpinion(event: OpinionUpdateEvent) {
        val command = event.command
        qna.updateOpinion(command)
    }

    @EventListener
    @Async
    fun deleteOpinion(event: OpinionDeleteEvent) {
        val command = event.command
        qna.deleteOpinion(command)
    }
}