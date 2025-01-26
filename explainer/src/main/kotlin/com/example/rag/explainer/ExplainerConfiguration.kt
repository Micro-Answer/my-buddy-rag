package com.example.rag.explainer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExplainerConfiguration {
    @Bean
    fun explainer(): Explainer =
        Explainer()
}