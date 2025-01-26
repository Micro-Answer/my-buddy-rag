package com.example.rag.explainer

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [ExplainerConfiguration::class])
class ExplainerUnitTests {
    @Autowired
    lateinit var explainer: Explainer

    @Test
    @DisplayName("test Explainer initialization")
    fun initialize() {
    }
}