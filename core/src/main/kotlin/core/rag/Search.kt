package core.rag

interface Search {
    fun search(query: String, age: Int, gender: String?, personalData: String?): String
}