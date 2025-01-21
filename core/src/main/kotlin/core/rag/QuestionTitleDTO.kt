package core.rag

interface QuestionTitleDTO {
    fun questionId(): String
    fun title(): String
    fun userId(): String
    fun createdDate(): String
}
