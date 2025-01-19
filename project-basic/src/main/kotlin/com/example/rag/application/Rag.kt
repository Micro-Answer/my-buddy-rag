package com.example.rag.application

import org.springframework.stereotype.Component

@Component
class Rag(private val searchableQnA: SearchableQnA) {
    init {
        println("create Rag")
    }
}
