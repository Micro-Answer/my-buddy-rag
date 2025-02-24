package com.example.util

import java.util.concurrent.atomic.AtomicLong
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import kotlin.concurrent.fixedRateTimer

class ThroughputMonitor(private val taskName: String, private val completedTasks: AtomicLong = AtomicLong(0)) {

    private val logFile = File("./throughput/${taskName}_throughput.log")
    private val writer = BufferedWriter(FileWriter(logFile, true))

    fun increment() {
        completedTasks.incrementAndGet()
    }

    fun startMonitoring() {
        fixedRateTimer("ThroughputMonitor", daemon = true, initialDelay = 0, period = 1000) {
            val tasksPerSecond = completedTasks.getAndSet(0)
            val logMessage = "$tasksPerSecond tasks/sec"
            writeLog(logMessage)
        }
    }

    private fun writeLog(message: String) {
        writer.write("$message\n")
        writer.flush()
    }

    fun stopMonitoring() {
        writer.close()
    }
}
