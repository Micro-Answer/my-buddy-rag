package com.example.rag.qna.adapter.output.test.initializer

import com.example.rag.qna.adapter.output.opinion.OpinionEntity
import com.example.rag.qna.adapter.output.opinion.OpinionRepository
import com.example.rag.qna.adapter.output.question.QuestionEntity
import com.example.rag.qna.adapter.output.question.QuestionRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class DataInitializer(
    private val questionRepository: QuestionRepository,
    private val opinionRepository: OpinionRepository
) : CommandLineRunner {
    @Throws(Exception::class)
    override fun run(vararg args: String) {
        for (i in 1..100000) {
            val question = QuestionEntity(
                "Category",
                "Title$i",
                "Content for question $i",
                "user$i",
                LocalDateTime.now(),
                LocalDateTime.now()
            )
            questionRepository.save(question)

            val opinion = OpinionEntity(
                i.toString(),
                "Opinion Title $i",
                "Content for opinion $i",
                "user$i",
                LocalDateTime.now(),
                LocalDateTime.now()
            )
            opinionRepository.save(opinion)
        }
    }
}
