package com.example.rag.application.event.opinion.store.port

import com.example.rag.application.event.opinion.store.adapter.OpinionEventStore
import java.util.*

interface ReadOnlyOpinionEvent {
    /**
     * 주어진 opinionId에 대한 이벤트 히스토리를 조회하는 메서드
     */
    fun findByOpinionId(opinionId: UUID): List<OpinionEventStore>

    fun findById(id: Long): OpinionEventStore
}
