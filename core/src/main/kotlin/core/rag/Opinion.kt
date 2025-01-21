package core.rag

interface Opinion {
    fun enrollOpinion(userId: String?, questionId: String?, title: String?, contents: String?): String?
    fun updateOpinion(userId: String?, opinionId: String?, title: String?, contents: String?): String?
    fun deleteOpinion(userId: String?, opinionId: String?): String?
    fun readOpinions(questionId: String?, startNum: Int, endNum: Int): Array<OpinionDTO?>?
}

