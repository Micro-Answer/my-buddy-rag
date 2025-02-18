package com.example.util.cache.lru

import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class LRUCache<K, V>(
    private val list: DoublyLinkedList<K, V> = DoublyLinkedList(),
    private var map: MutableMap<K, Node<K, V>> = HashMap(),
    private val max: Int = 1000,
    private val expirationPeriod: Long = 24 * 60 * 60 * 1000
) {
    private val lock = ReentrantLock()

    private val timer = Timer().apply {
        scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                removeExpiredEntries()
            }
        }, 0, expirationPeriod)
    }

    private fun removeExpiredEntries() {
        lock.withLock {
            map.entries.removeIf { (key, value) ->
                value.isExpired().also { if (it) list.remove(value) }
            }
        }
    }

    fun get(key: K): V? =
        map[key]?. let {
            list.moveToHead(it)
            it.value
        }

    fun put(key: K, value: V) {
        lock.withLock {
            map[key]?. let {
                it.value = value
                list.moveToHead(it)
            } ?: run {
                if (map.size >= max) {
                    reduce((max * 0.2).toInt())
                }
                with(Node(key, value)) {
                    list.addFirst(this)
                    map[key] = this
                }
            }
        }
    }

    private fun reduce(size: Int) {
        lock.withLock {
            repeat(size) {
                list.removeLast().let {
                    map.remove(it.key)
                }
            }
        }
    }

    fun stopTimer() {
        timer.cancel()
    }
}