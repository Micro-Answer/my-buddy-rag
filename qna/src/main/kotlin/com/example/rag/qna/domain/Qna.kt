package com.example.rag.qna.domain

import com.example.rag.qna.adapter.output.opinion.OpinionPersistencePort
import com.example.rag.qna.adapter.output.question.QuestionPersistencePort
import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import org.springframework.stereotype.Component

@Component
class Qna(private val questionPersistence: QuestionPersistencePort, private val opinionPersistence: OpinionPersistencePort): QnaSystem {

    override fun enrollQuestion(userId: String, title: String, category: String, content: String)
        = questionPersistence.saveQuestion( Question(category, title, content, userId) ).also {
            // 캐시 등록
        }

    override fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String) {
        questionPersistence.updateQuestion( Question(questionId, category, title, content, userId) )
    }

    override fun deleteQuestion(userId: String, questionId: String) {
        questionPersistence.deleteQuestion(questionId)
    }

    override fun readQuestion(questionId: String): Question
        = questionPersistence.findQuestionById(questionId)

    // 논리 오류 수정 대상
    override fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle>
        = questionPersistence.findQuestionsByUserId(category, offset, limit)
                             .filter { !it.questionId.isNullOrBlank() }
                             .map { QuestionTitle(it.questionId!!, it.title, it.userId, it.createdAt!!) }

    override fun enrollOpinion(userId: String, questionId: String, title: String, content: String) {
        opinionPersistence.saveOpinion( Opinion(questionId, title, content, userId) )
    }

    override fun updateOpinion(userId: String, opinionId: String, title: String, content: String) {
        opinionPersistence.updateOpinion( Opinion("questionId", title, content, userId, opinionId) )
    }

    override fun deleteOpinion(userId: String, opinionId: String) {
        opinionPersistence.deleteOpinionById(opinionId)
    }

    override fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion>
        = opinionPersistence.findOpinionsByQuestionId(questionId, offset, limit)
}