package core.qna

import core.rag.Question
import core.rag.QuestionTitleDTO

interface CacheSystem {
    fun getRecentQuestionTitles(category: String, offset: Int, limit: Int): Array<QuestionTitleDTO>
    fun putRecentQuestionTitle(category: String, questionTitle: QuestionTitleDTO)
    fun getRecentQuestion(questionId: String): Question
    fun putRecentQuestion(questionId: String, question: Question)
}