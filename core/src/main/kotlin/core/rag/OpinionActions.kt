package core.rag

interface OpinionActions {
    fun enrollOpinion(userId: String, questionId: String, title: String, content: String)
    fun updateOpinion(userId: String, opinionId: String, title: String, content: String)
    fun deleteOpinion(userId: String, opinionId: String)
    fun readOpinions(questionId: String, offset: Int, limit: Int): List<Opinion>
}

