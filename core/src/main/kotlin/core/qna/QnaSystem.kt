package core.qna

import core.rag.OpinionDTO
import core.rag.QuestionDTO
import core.rag.QuestionTitleDTO

interface QnaSystem {
    fun enrollQuestion(userId: String, title: String, category: String, content: String): String
    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String): String
    fun deleteQuestion(userId: String, questionId: String): String
    fun readQuestion(questionId: String): QuestionDTO
    fun readQuestionTitles(category: String, offset: Int, limit: Int): Array<QuestionTitleDTO>

    fun enrollOpinion(userId: String, questionId: String, title: String, content: String): String
    fun updateOpinion(userId: String, opinionId: String, title: String, content: String): String
    fun deleteOpinion(userId: String, opinionId: String): String
    fun readOpinions(questionId: String, offset: Int, limit: Int): Array<OpinionDTO>
}