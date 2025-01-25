package com.example.rag.application.command.consumer

import core.rag.event.QnAEvent
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

class Consumer<T : QnAEvent>(
    private val queue: BlockingQueue<T>,
    private val executorService: ExecutorService,
    private val nThreads: Int,
    private val taskLogic: (T) -> Unit,
) {
    @Volatile private var running: Boolean = true

    fun init() {
        repeat(nThreads) {
            executorService.submit {
                while (running) {
                    task()
                }
            }
        }
    }

    fun cleanup() {
        running = false
        queue.forEach { task() }
        executorService.shutdown()
        if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
            executorService.shutdownNow()
        }
    }

    private fun task() {
        queue.poll(1, TimeUnit.SECONDS)?.let { event ->
            taskLogic(event)
        }
    }
}
