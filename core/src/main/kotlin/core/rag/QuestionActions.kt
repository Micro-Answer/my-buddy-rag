package core.rag

import core.rag.event.QnAEvent

interface QuestionActions {
    fun execute(event: QnAEvent)
    fun readQuestion(questionId: String): Question
    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle>
}