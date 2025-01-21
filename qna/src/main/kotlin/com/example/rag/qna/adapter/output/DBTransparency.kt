package com.example.rag.qna.adapter.output

object DBTransparency {
    fun idForTransparency(opinionId: Long?) = opinionId.toString()
    fun idForMySQL(opinionId: String?) = opinionId?.toLong() ?: -1
}