package com.example.rag.application.event.opinion.store.adapter

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OpinionEventRepository: JpaRepository<OpinionEventStore, Long> {
    fun findByOpinionId(opinionId: UUID): List<OpinionEventStore>
}