package core.qna

import core.rag.QuestionDTO
import core.rag.QuestionTitleDTO

interface CacheSystem {
    fun getRecentQuestionTitles(category: String?, startNum: Int, endNum: Int): Array<QuestionTitleDTO?>?
    fun putRecentQuestionTitle(category: String?, questionTitle: QuestionTitleDTO?)
    fun getRecentQuestion(questionId: String?): QuestionDTO?
    fun putRecentQuestion(questionId: String?, question: QuestionDTO?)
}