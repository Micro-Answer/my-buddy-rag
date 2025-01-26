package com.example.rag.application.command

import kotlinx.coroutines.*

class Consumer(
    private val task: () -> Unit
) {
    private val applicationScope: CoroutineScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, exception ->
            println("Caught exception: ${exception.message}")
        })

    fun init() {
        applicationScope.launch {
            while (isActive) {
                task()
            }
        }
    }

    fun cleanup() {
        applicationScope.cancel()
    }
}