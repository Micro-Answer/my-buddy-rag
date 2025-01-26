package core.rag.event

sealed class QnAEvent  {
    data class EnrollQuestion(val category: String, val title: String, val content: String, val userId: String) : QnAEvent()
    data class UpdateQuestion(val category: String, val title: String, val content: String, val userId: String, val questionId: String) : QnAEvent()
    data class DeleteQuestion(val questionId: String, val userId: String) : QnAEvent()
    data class EnrollOpinion(val questionId: String, val title: String, val content: String, val userId: String) : QnAEvent()
    data class UpdateOpinion(val title: String, val content: String, val userId: String, val opinionId: String) : QnAEvent()
    data class DeleteOpinion(val opinionId: String, val userId: String) : QnAEvent()
    object Shutdown : QnAEvent()
}