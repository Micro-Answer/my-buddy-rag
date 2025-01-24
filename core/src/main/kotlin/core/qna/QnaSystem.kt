package core.qna

import core.rag.Opinion
import core.rag.Question
import core.rag.QuestionTitle
import core.rag.event.QnAEvent

interface QnaSystem {
    fun enrollQuestion(event: QnAEvent.EnrollQuestion): Question
    fun updateQuestion(event: QnAEvent.UpdateQuestion)
    fun deleteQuestion(event: QnAEvent.DeleteQuestion)
    fun readQuestion(questionId: String): Question
    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle>

    fun enrollOpinion(event: QnAEvent.EnrollOpinion)
    fun updateOpinion(event: QnAEvent.UpdateOpinion)
    fun deleteOpinion(event: QnAEvent.DeleteOpinion)
    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion>
}