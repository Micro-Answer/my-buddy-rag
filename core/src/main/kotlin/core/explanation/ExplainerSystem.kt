package core.explanation

interface ExplainerSystem {
    fun explain(content: String, personalData: String?): String
}