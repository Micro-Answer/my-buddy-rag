package com.example.rag.qna.adapter.output.question

import com.example.rag.qna.model.Question
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

private fun Question.createEntity() =
    QuestionEntity(id, category, title, content, userId, LocalDateTime.now())

private fun QuestionEntity.toDomainModel() =
    Question(id, category, title, content, userId, createdAt, updatedAt)

private infix fun QuestionEntity.updateWith(domain: Question) {
    category = domain.category
    title = domain.title
    content = domain.content
    updatedAt = LocalDateTime.now()
}

@Component
class QuestionSnapshotAdapter(private val questionRepository: QuestionRepository) : QuestionSnapshotPort {
    @Transactional
    override fun save(domain: Question): Question =
        questionRepository.save(domain.createEntity())
            .toDomainModel()

    @Transactional
    override fun update(domain: Question) {
        val questionEntity = getQuestionEntity(domain.id)
        questionEntity updateWith domain
        questionRepository.save(questionEntity)
    }

    @Transactional
    override fun deleteById(id: UUID) {
        questionRepository.deleteById(id)
    }

    private fun getQuestionEntity(id: UUID): QuestionEntity =
        questionRepository.findById(id).orElseThrow()
}