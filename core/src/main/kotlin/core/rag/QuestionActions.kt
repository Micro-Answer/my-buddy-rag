package core.rag

interface QuestionActions {
    fun enrollQuestion(userId: String, title: String, category: String, content: String): String
    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String): String
    fun deleteQuestion(userId: String, questionId: String): String
    fun readQuestion(questionId: String): Question
    fun readQuestionTitles(category: String, offset: Int, limit: Int): Array<QuestionTitle>
}