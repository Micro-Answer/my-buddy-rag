package core.qna

import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitleDTO

interface QnaSystem {
    fun enrollQuestion(userId: String, title: String, category: String, content: String): Unit
    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String): Unit
    fun deleteQuestion(userId: String, questionId: String): Unit
    fun readQuestion(questionId: String): Question
    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitleDTO>

    fun enrollOpinion(userId: String, questionId: String, title: String, content: String): Unit
    fun updateOpinion(userId: String, opinionId: String, title: String, content: String): Unit
    fun deleteOpinion(userId: String, opinionId: String): Unit
    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion>
}