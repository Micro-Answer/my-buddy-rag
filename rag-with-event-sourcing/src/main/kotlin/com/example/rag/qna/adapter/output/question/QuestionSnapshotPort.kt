package com.example.rag.qna.adapter.output.question

import com.example.rag.qna.model.Question
import java.util.UUID

interface QuestionSnapshotPort {
    fun save(domain: Question): Question
    fun update(domain: Question)
    fun deleteById(id: UUID)
}