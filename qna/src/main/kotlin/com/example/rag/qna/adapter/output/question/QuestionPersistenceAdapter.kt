package com.example.rag.qna.adapter.output.question

import com.example.rag.qna.adapter.output.DBTransparency.idForMySQL
import com.example.rag.qna.adapter.output.DBTransparency.idForTransparency
import com.example.rag.qna.domain.Question
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class QuestionPersistenceAdapter(private val questionRepository: QuestionRepository) : QuestionPersistencePort {
    private companion object QuestionMapper {
        fun Question.createEntity() = QuestionEntity(getCategory(), getTitle(), getContent(), getUserId(), LocalDateTime.now())
        fun QuestionEntity.toDomainModel() = Question(idForTransparency(id), category, title, content, userId, createdAt, updatedAt)
        fun QuestionEntity.updateWithDomain(domain: Question) {
            category = domain.getCategory()
            title = domain.getTitle()
            content = domain.getContent()
            updatedAt = domain.getUpdatedAt()!!
        }
    }

    override fun saveQuestion(domain: Question): Question = questionRepository.save(domain.createEntity()).toDomainModel()
    override fun updateQuestion(domain: Question): Unit = getQuestionEntity(domain.getQuestionId()!!).updateWithDomain(domain)
    override fun deleteQuestion(questionId: String): Unit = questionRepository.deleteById(idForMySQL(questionId))
    override fun findQuestionById(questionId: String): Question = getQuestionEntity(questionId).toDomainModel()
    override fun findQuestionsByUserId(userId: String, offset: Int, limit: Int) = getQuestions(userId, offset, limit)

    private fun getQuestionEntity(questionId: String): QuestionEntity = questionRepository.findById(idForMySQL(questionId)).orElseThrow()
    private fun getQuestions(userId: String, offset: Int, limit: Int) = questionRepository.findByUserId(userId, offset, limit)
                                                                                          .map { entity -> entity.toDomainModel() }
}