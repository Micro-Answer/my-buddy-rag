package core.rag

interface QuestionActions {
    fun enrollQuestion(userId: String, title: String, category: String, content: String)
    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String)
    fun deleteQuestion(userId: String, questionId: String)
    fun readQuestion(questionId: String): Question
    fun readQuestionTitles(category: String, offset: Int, limit: Int): List<QuestionTitle>
}