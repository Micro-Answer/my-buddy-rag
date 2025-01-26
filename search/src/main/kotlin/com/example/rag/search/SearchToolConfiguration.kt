package com.example.rag.search

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SearchToolConfiguration {
    @Bean
    fun searchTool(): SearchTool =
        SearchTool()
}