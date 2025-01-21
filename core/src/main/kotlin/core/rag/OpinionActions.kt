package core.rag

interface OpinionActions {
    fun enrollOpinion(userId: String, questionId: String, title: String, content: String): String
    fun updateOpinion(userId: String, opinionId: String, title: String, content: String): String
    fun deleteOpinion(userId: String, opinionId: String): String
    fun readOpinions(questionId: String, offset: Int, limit: Int): Array<Opinion>
}

