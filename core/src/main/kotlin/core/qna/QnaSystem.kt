package core.qna

import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle

interface QnaSystem {
    fun enrollQuestion(userId: String, title: String, category: String, content: String): Question
    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String)
    fun deleteQuestion(userId: String, questionId: String)
    fun readQuestion(questionId: String): Question
    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle>

    fun enrollOpinion(userId: String, questionId: String, title: String, content: String)
    fun updateOpinion(userId: String, opinionId: String, title: String, content: String)
    fun deleteOpinion(userId: String, opinionId: String)
    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion>
}