package com.example.rag.application.util

import java.util.concurrent.CompletableFuture

fun runAsync(task: () -> Unit): Unit {
    try {
        CompletableFuture.runAsync(task)
    } catch (e: Exception) {
        println("Error in async operation: ${e.message}")
    }
}