package core.rag

import core.rag.event.QnAEvent

interface OpinionActions {
    fun execute(event: QnAEvent)
    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion>
}

