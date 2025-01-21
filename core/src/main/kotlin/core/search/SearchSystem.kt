package core.search

interface SearchSystem {
    fun search(title: String?, category: String?, contents: String?): String?
    fun enrollQuestion(questionId: String?, title: String?, category: String?, contents: String?): String?
    fun updateQuestion(questionId: String?, title: String?, category: String?, contents: String?): String?
    fun deleteQuestion(questionId: String?): String?
}
