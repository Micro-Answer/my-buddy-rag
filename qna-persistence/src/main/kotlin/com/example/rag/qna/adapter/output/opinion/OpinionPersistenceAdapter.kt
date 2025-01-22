package com.example.rag.qna.adapter.output.opinion

import com.example.rag.qna.adapter.output.DBTransparency.idForMySQL
import com.example.rag.qna.adapter.output.DBTransparency.idForTransparency
import core.rag.Opinion
import org.springframework.stereotype.Component
import java.time.LocalDateTime

private fun Opinion.createEntity() =
    OpinionEntity(questionId, title, content, userId, LocalDateTime.now())

private fun OpinionEntity.toDomainModel() =
    Opinion(questionId, title, content, userId, idForTransparency(id), createdAt, updatedAt)

private infix fun OpinionEntity.updateWith(domain: Opinion) {
    title = domain.title
    content = domain.content
    updatedAt = LocalDateTime.now()
}

@Component
class OpinionPersistenceAdapter(private val opinionRepository: OpinionRepository) : OpinionPersistencePort {

    override fun saveOpinion(domain: Opinion) {
        opinionRepository.save(domain.createEntity())
    }

    override fun updateOpinion(domain: Opinion) {
        getOpinionEntity(domain.questionId) updateWith domain
    }

    override fun deleteOpinionById(opinionId: String) {
        opinionRepository.deleteById(idForMySQL(opinionId))
    }

    override fun findOpinionById(opinionId: String): Opinion =
        getOpinionEntity(opinionId).toDomainModel()

    override fun findOpinionsByQuestionId(questionId: String, offset: Int, limit: Int): List<Opinion> =
        opinionRepository.findByQuestionId(questionId, offset, limit)
            .map { it.toDomainModel() }

    private fun getOpinionEntity(opinionId: String?): OpinionEntity =
        opinionRepository.findById(idForMySQL(opinionId))
            .orElseThrow()
}