package com.example.rag.qna.adapter.output.opinion

import com.example.rag.qna.model.Opinion
import java.util.*

interface OpinionSnapshotPort {
    fun save(domain: Opinion)
    fun update(domain: Opinion)
    fun deleteById(id: UUID)
}