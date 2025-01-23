package com.example.rag.qna.adapter.output.question

import com.example.rag.qna.adapter.output.DBTransparency.idForMySQL
import com.example.rag.qna.adapter.output.DBTransparency.idForTransparency
import core.rag.Question
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.time.LocalDateTime

private fun Question.createEntity() =
    QuestionEntity(category, title,content, userId, LocalDateTime.now())

private fun QuestionEntity.toDomainModel() =
    Question(idForTransparency(id), category, title, content, userId, createdAt, updatedAt)

private infix fun QuestionEntity.updateWith(domain: Question) {
    category = domain.category
    title = domain.title
    content = domain.content
    updatedAt = LocalDateTime.now()
}

@Component
class QuestionPersistenceAdapter(private val questionRepository: QuestionRepository) : QuestionPersistencePort {

    override fun saveQuestion(domain: Question): Question =
        questionRepository.save(domain.createEntity())
            .toDomainModel()

    override fun updateQuestion(domain: Question) {
        val questionEntity = getQuestionEntity(domain.questionId)
        questionEntity updateWith domain
        questionRepository.save(questionEntity)
    }

    override fun deleteQuestion(questionId: String) {
        questionRepository.deleteById(idForMySQL(questionId))
    }

    override fun findQuestionById(questionId: String): Question =
        getQuestionEntity(questionId).toDomainModel()

    override fun findQuestionsByCategory(category: String, offset: Int, limit: Int): List<Question> =
        questionRepository.findByCategory(category, PageRequest.of(offset, limit))
            .content.map { it.toDomainModel() }

    override fun findQuestionsByUserId(userId: String, offset: Int, limit: Int): List<Question> =
        questionRepository.findByUserId(userId, PageRequest.of(offset, limit))
            .content.map { it.toDomainModel() }

    private fun getQuestionEntity(questionId: String?): QuestionEntity =
        questionRepository.findById(idForMySQL(questionId)).orElseThrow()
}