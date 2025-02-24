package com.example.rag.application.event.opinion.store.port

import com.example.rag.application.event.opinion.store.adapter.OpinionEventStore

interface WriteOnlyOpinionEvent {
    /**
     * Opinion 관련 이벤트를 기록하는 메서드
     */
    fun recordOpinionEvent(event: OpinionEventStore): Long
}
