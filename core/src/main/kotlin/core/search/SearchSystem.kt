package core.search

interface SearchSystem {
    fun search(title: String, category: String, content: String): String
    fun enrollQuestion(questionId: String, title: String, category: String, content: String): String
    fun updateQuestion(questionId: String, title: String, category: String, content: String): String
    fun deleteQuestion(questionId: String): String
}
