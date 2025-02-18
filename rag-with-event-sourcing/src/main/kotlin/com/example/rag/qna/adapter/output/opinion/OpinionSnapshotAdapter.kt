package com.example.rag.qna.adapter.output.opinion

import com.example.rag.qna.model.Opinion
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

private fun Opinion.createEntity() =
    OpinionEntity(id, questionId, title, content, userId, LocalDateTime.now())

private infix fun OpinionEntity.updateWith(domain: Opinion) {
    title = domain.title
    content = domain.content
    updatedAt = LocalDateTime.now()
}

@Component
class OpinionSnapshotAdapter(private val opinionRepository: OpinionRepository): OpinionSnapshotPort {
    @Transactional
    override fun save(domain: Opinion) {
        opinionRepository.save(domain.createEntity())
    }

    @Transactional
    override fun update(domain: Opinion) {
        val opinionEntity = opinionRepository.findById(domain.id).orElseThrow()
        opinionEntity updateWith domain
        opinionRepository.save(opinionEntity)
    }

    @Transactional
    override fun deleteById(id: UUID) {
        opinionRepository.deleteById(id)
    }
}