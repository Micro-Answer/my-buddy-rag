package core.rag

interface Question {
    fun enrollQuestion(userId: String?, title: String?, category: String?, contents: String?): String?
    fun updateQuestion(userId: String?, questionId: String?, category: String?, contents: String?): String?
    fun deleteQuestion(userId: String?, questionId: String?): String?
    fun readQuestion(questionId: String?): QuestionDTO?
    fun readQuestionTitles(category: String?, startNum: Int, endNum: Int): Array<QuestionTitleDTO?>?
}