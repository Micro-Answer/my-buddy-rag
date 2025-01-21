package core.rag

interface Search {
    fun search(contents: String, age: Int, gender: String?, personalData: String?): String
}