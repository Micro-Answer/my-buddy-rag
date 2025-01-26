package com.example.rag.qna.domain

import com.example.rag.qna.adapter.output.opinion.OpinionPersistencePort
import com.example.rag.qna.adapter.output.question.QuestionPersistencePort
import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.rag.event.QnAEvent

class Qna(private val questionPersistence: QuestionPersistencePort, private val opinionPersistence: OpinionPersistencePort): QnaSystem {
    override fun enrollQuestion(event: QnAEvent.EnrollQuestion) =
        questionPersistence.saveQuestion(event.toQuestion()).also {
            // 캐시 등록
        }

    override fun updateQuestion(event: QnAEvent.UpdateQuestion) {
        if (event.userId == questionPersistence.getUserIdByQuestionId(event.questionId))
            questionPersistence.updateQuestion(event.toQuestion())
    }

    override fun deleteQuestion(event: QnAEvent.DeleteQuestion) {
        if (event.userId == questionPersistence.getUserIdByQuestionId(event.questionId))
            questionPersistence.deleteQuestion(event.questionId)
    }

    override fun readQuestion(questionId: String): Question =
        questionPersistence.findQuestionById(questionId)

    override fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle> =
        questionPersistence.findQuestionsByCategory(category, offset, limit)
            .filter { !it.questionId.isNullOrBlank() }
            .map { QuestionTitle(it.questionId!!, it.title, it.userId, it.createdAt!!) }

    override fun enrollOpinion(event: QnAEvent.EnrollOpinion) {
        opinionPersistence.saveOpinion(event.toOpinion())
    }

    override fun updateOpinion(event: QnAEvent.UpdateOpinion) {
        if (event.userId == opinionPersistence.getUserIdByOpinionId(event.opinionId))
            opinionPersistence.updateOpinion(event.toOpinion())
    }

    override fun deleteOpinion(event: QnAEvent.DeleteOpinion) {
        if (event.userId == opinionPersistence.getUserIdByOpinionId(event.opinionId))
            opinionPersistence.deleteOpinionById(event.opinionId)
    }

    override fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> =
        opinionPersistence.findOpinionsByQuestionId(questionId, offset, limit)

    companion object {
        fun QnAEvent.EnrollQuestion.toQuestion(): Question =
            Question(category, title, content, userId)

        fun QnAEvent.UpdateQuestion.toQuestion(): Question =
            Question(category, title, content, userId, questionId)

        fun QnAEvent.EnrollOpinion.toOpinion(): Opinion =
            Opinion(questionId, title, content, userId)

        fun QnAEvent.UpdateOpinion.toOpinion(): Opinion =
            Opinion("not used", title, content, userId, opinionId)
    }
}