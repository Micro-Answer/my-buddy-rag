package com.example.rag.qna.adapter.output.question

import com.example.rag.qna.adapter.output.DBTransparency.idForMySQL
import com.example.rag.qna.adapter.output.DBTransparency.idForTransparency
import core.rag.Question
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class QuestionPersistenceAdapter(private val questionRepository: QuestionRepository) : QuestionPersistencePort {
    private companion object QuestionMapper {
        fun Question.createEntity() = QuestionEntity(category, title,content, userId, LocalDateTime.now())
        fun QuestionEntity.toDomainModel() = Question(idForTransparency(id), category, title, content, userId, createdAt, updatedAt)
        fun QuestionEntity.updateWithDomain(domain: Question) {
            category = domain.category
            title = domain.title
            content = domain.content
            updatedAt = LocalDateTime.now()
        }
    }

    override fun saveQuestion(domain: Question): Question = questionRepository.save(domain.createEntity()).toDomainModel()
    override fun updateQuestion(domain: Question): Unit = getQuestionEntity(domain.questionId).updateWithDomain(domain)
    override fun deleteQuestion(questionId: String): Unit = questionRepository.deleteById(idForMySQL(questionId))
    override fun findQuestionById(questionId: String): Question = getQuestionEntity(questionId).toDomainModel()
    override fun findQuestionsByUserId(userId: String, offset: Int, limit: Int) = getQuestions(userId, offset, limit)

    private fun getQuestionEntity(questionId: String?): QuestionEntity = questionRepository.findById(idForMySQL(questionId)).orElseThrow()
    private fun getQuestions(userId: String, offset: Int, limit: Int) = questionRepository.findByUserId(userId, offset, limit)
                                                                                          .map { entity -> entity.toDomainModel() }
}