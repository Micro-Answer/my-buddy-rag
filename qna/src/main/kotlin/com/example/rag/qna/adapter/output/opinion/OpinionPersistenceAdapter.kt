package com.example.rag.qna.adapter.output.opinion

import com.example.rag.qna.domain.Opinion
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class OpinionPersistenceAdapter(private val opinionRepository: OpinionRepository) : OpinionPersistencePort {
    private companion object OpinionMapper {
        fun idForTransparency(opinionId: Long?) = opinionId.toString()
        fun idForMySQL(opinionId: String?) = opinionId?.toLong() ?: -1

        fun Opinion.createEntity() = OpinionEntity(getQuestionId(), getTitle(), getContent(), getUserId(), LocalDateTime.now())
        fun OpinionEntity.toDomainModel() = Opinion(idForTransparency(id), questionId, title, content, userId, createdAt, updatedAt)
        fun OpinionEntity.updateWithDomain(domain: Opinion) {
            this.title = domain.getTitle()
            this.content = domain.getContent()
            this.updatedAt = LocalDateTime.now()
        }
    }

    override fun saveOpinion(domain: Opinion): OpinionEntity = opinionRepository.save(domain.createEntity())
    override fun updateOpinion(domain: Opinion) = getOpinionEntity(domain.getOpinionId()).updateWithDomain(domain)
    override fun deleteOpinion(opinionId: String): Unit = opinionRepository.deleteById(idForMySQL(opinionId))
    override fun findOpinionById(opinionId: String): Opinion = getOpinionEntity(opinionId).toDomainModel()
    override fun findOpinionsByQuestionId(questionId: String, offset: Int, limit: Int): List<Opinion> = getOpinions(questionId, offset, limit)

    private fun getOpinionEntity(opinionId: String?): OpinionEntity = opinionRepository.findById(idForMySQL(opinionId)).orElseThrow()
    private fun getOpinions(questionId: String, offset: Int, limit: Int) = opinionRepository.findByQuestionId(questionId, offset, limit)
                                                                                            .map { entity -> entity.toDomainModel() }
}
