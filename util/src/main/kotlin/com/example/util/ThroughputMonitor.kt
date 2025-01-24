package com.example.util

import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.fixedRateTimer

class ThroughputMonitor(private val taskName: String, private val completedTasks:AtomicLong = AtomicLong(0)) {

    // 작업이 완료될 때 호출
    fun increment() {
        completedTasks.incrementAndGet()
    }

    // 초당 처리량을 출력
    fun startMonitoring() {
        fixedRateTimer("ThroughputMonitor", daemon = true, initialDelay = 0, period = 1000) {
            val tasksPerSecond = completedTasks.getAndSet(0) // 1초간 처리된 작업 수
            println("$taskName Throughput: $tasksPerSecond tasks/sec")
        }
    }
}