package com.example.rag.qna.domain

import com.example.rag.qna.adapter.output.opinion.OpinionPersistencePort
import com.example.rag.qna.adapter.output.question.QuestionPersistencePort
import core.qna.QnaSystem
import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitleDTO
import org.springframework.stereotype.Component

@Component
class Qna(private val questionPersistencePort: QuestionPersistencePort, private val opinionPersistencePort: OpinionPersistencePort): QnaSystem {
    override fun enrollQuestion(userId: String, title: String, category: String, content: String): Unit = enroll(userId, title, category, content)
    override fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String): Unit = update(userId, questionId, title, category, content)
    override fun deleteQuestion(userId: String, questionId: String): Unit = questionPersistencePort.deleteQuestion(questionId)
    override fun readQuestion(questionId: String): Question = questionPersistencePort.findQuestionById(questionId)
    // 논리 오류 수정 대상 
    override fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitleDTO> = questionPersistencePort.findQuestionsByUserId(category, offset, limit).map { question -> QuestionTitle(question.questionId, question.title, question.userId, question.createdAt) }
    override fun enrollOpinion(userId: String, questionId: String, title: String, content: String): Unit = opinionPersistencePort.saveOpinion(Opinion(questionId, title, content, userId))
    override fun updateOpinion(userId: String, opinionId: String, title: String, content: String): Unit = opinionPersistencePort.updateOpinion(Opinion("questionId", title, content, userId, opinionId))
    override fun deleteOpinion(userId: String, opinionId: String): Unit = opinionPersistencePort.deleteOpinion(opinionId)
    override fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion> = opinionPersistencePort.findOpinionsByQuestionId(questionId, offset, limit)

    private fun enroll(userId: String, title: String, category: String, content: String): Unit =
        synchronized(this) {
            questionPersistencePort.saveQuestion(Question(category, title, content, userId))
        }
    private fun update(userId: String, questionId: String, title: String, category: String, content: String): Unit = questionPersistencePort.updateQuestion(Question(questionId, category, title, content, userId))
}