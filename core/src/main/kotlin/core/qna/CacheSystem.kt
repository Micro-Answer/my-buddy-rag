package core.qna

import core.rag.Question
import core.rag.QuestionTitle

interface CacheSystem {
    fun getRecentQuestionTitles(category: String, offset: Int, limit: Int): Array<QuestionTitle>
    fun putRecentQuestionTitle(category: String, questionTitle: QuestionTitle)
    fun getRecentQuestion(questionId: String): Question
    fun putRecentQuestion(questionId: String, question: Question)
}