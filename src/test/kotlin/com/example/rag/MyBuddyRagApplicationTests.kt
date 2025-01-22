package com.example.rag

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean
import kotlin.system.measureTimeMillis

@SpringBootTest
class MyBuddyRagApplicationTests {

	@Test
	fun contextLoads() {
	}

	// 코드 참고 : 카카오 페이 테크 블로그 "코루틴과 Virtual Thread 비교와 사용"
	fun measurePerformanceMetric(task: () -> Unit) {
		val runtime = Runtime.getRuntime()
		val threadMXBean: ThreadMXBean = ManagementFactory.getThreadMXBean()

		val initialMemory = runtime.totalMemory() - runtime.freeMemory()
		val initialCpuTime = threadMXBean.currentThreadCpuTime

		val time = measureTimeMillis { task() }

		val finalMemory: Long = runtime.totalMemory() - runtime.freeMemory()
		val finalCpuTime: Long = threadMXBean.currentThreadCpuTime

		val memoryUsed = finalMemory - initialMemory
		val cpuTimeUsed = finalCpuTime - initialCpuTime

		println("Memory used: $memoryUsed bytes")
		println("CPU time used: $cpuTimeUsed ns")
		println("Execution Time: $time ms")
	}
}
