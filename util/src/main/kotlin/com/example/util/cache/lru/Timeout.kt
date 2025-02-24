package com.example.util.cache.lru

import java.util.concurrent.TimeUnit

class Timeout(
    private val timeout: Long = TimeUnit.DAYS.toMillis(1),
    private val createdAt: Long = System.currentTimeMillis()
) {
    fun isExpired(): Boolean =
        System.currentTimeMillis() - createdAt > timeout
}