package com.example.util.cache.lru

class DoublyLinkedList<K, V>(
    private var head: Node<K, V>? = null,
    private var tail: Node<K, V>? = null
) {
    fun addFirst(node: Node<K, V>) {
        node.next = head
        head?.prev = node
        head = node

        if (tail == null) {
            tail = head
        }
    }

    fun removeLast(): Node<K, V> {
        val last = tail ?: throw IllegalStateException("Cannot remove from an empty list")
        remove(last)
        return last
    }

    fun moveToHead(node: Node<K, V>) {
        if (node != head) {
            remove(node)
            addFirst(node)
        }
    }

    fun remove(node: Node<K, V>) {
        if (node == head) {
            head = node.next
        } else {
            node.prev?.next = node.next
        }

        if (node == tail) {
            tail = node.prev
        } else {
            node.next?.prev = node.prev
        }
    }
}