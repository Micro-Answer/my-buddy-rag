package com.example.rag.qna.adapter.output.opinion

import com.example.rag.qna.adapter.output.DBTransparency.idForMySQL
import com.example.rag.qna.adapter.output.DBTransparency.idForTransparency
import core.rag.Opinion
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class OpinionPersistenceAdapter(private val opinionRepository: OpinionRepository) : OpinionPersistencePort {
    private companion object OpinionMapper {
        fun Opinion.createEntity() = OpinionEntity(questionId, title, content, userId, LocalDateTime.now())
        fun OpinionEntity.toDomainModel() = Opinion(questionId, title, content, userId, idForTransparency(id), createdAt, updatedAt)
        fun OpinionEntity.updateWithDomain(domain: Opinion) {
            this.title = domain.title
            this.content = domain.content
            this.updatedAt = LocalDateTime.now()
        }
    }

    override fun saveOpinion(domain: Opinion) { opinionRepository.save(domain.createEntity()) }
    override fun updateOpinion(domain: Opinion) = getOpinionEntity(domain.questionId).updateWithDomain(domain)
    override fun deleteOpinion(opinionId: String) { opinionRepository.deleteById(idForMySQL(opinionId)) }
    override fun findOpinionById(opinionId: String): Opinion = getOpinionEntity(opinionId).toDomainModel()
    override fun findOpinionsByQuestionId(questionId: String, offset: Int, limit: Int): List<Opinion> = getOpinions(questionId, offset, limit)

    private fun getOpinionEntity(opinionId: String?): OpinionEntity = opinionRepository.findById(idForMySQL(opinionId)).orElseThrow()
    private fun getOpinions(questionId: String, offset: Int, limit: Int) = opinionRepository.findByQuestionId(questionId, offset, limit)
                                                                                            .map { entity -> entity.toDomainModel() }
}
