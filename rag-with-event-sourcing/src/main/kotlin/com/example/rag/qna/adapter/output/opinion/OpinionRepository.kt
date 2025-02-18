package com.example.rag.qna.adapter.output.opinion

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OpinionRepository: JpaRepository<OpinionEntity, UUID>