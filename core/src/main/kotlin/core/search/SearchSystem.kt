package core.search

import core.rag.event.QnAEvent

interface SearchSystem {
    fun search(query: String): String
    fun search(query: String, category: String): String
    fun enrollQuestion(questionId: String, event: QnAEvent.EnrollQuestion): String
    fun updateQuestion(event: QnAEvent.UpdateQuestion): String
    fun deleteQuestion(event: QnAEvent.DeleteQuestion): String
}
