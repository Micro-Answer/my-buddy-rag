package core.rag

interface Question {
    fun enrollQuestion(userId: String, title: String, category: String, content: String): String
    fun updateQuestion(userId: String, questionId: String, title: String, category: String, content: String): String
    fun deleteQuestion(userId: String, questionId: String): String
    fun readQuestion(questionId: String): QuestionDTO
    fun readQuestionTitles(category: String, offset: Int, limit: Int): Array<QuestionTitleDTO>
}