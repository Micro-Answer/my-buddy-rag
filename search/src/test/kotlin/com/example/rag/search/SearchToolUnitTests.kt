package com.example.rag.search

import core.search.SearchSystem
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [SearchToolConfiguration::class])
class SearchToolUnitTests {
    @Autowired
    lateinit var search: SearchSystem

    @Test
    @DisplayName("test search initialization")
    fun initialize() {
    }
}