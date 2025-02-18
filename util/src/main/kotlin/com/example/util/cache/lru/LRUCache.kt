package com.example.util.cache.lru

class LRUCache<K, V>(
    private val list: DoublyLinkedList<K, V> = DoublyLinkedList(),
    private var map: MutableMap<K, Node<K, V>> = HashMap(),
    private val max: Int = 1000
) {
    fun get(key: K): V? =
        map[key]?. let {
            list.moveToHead(it)
            it.value
        }

    @Synchronized
    fun put(key: K, value: V) {
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

    @Synchronized
    private fun reduce(size: Int) {
        repeat(size) {
            list.removeLast().let {
                map.remove(it.key)
            }
        }
    }
}