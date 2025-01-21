package core.explanation

interface ExplainerSystem {
    fun explain(contents: String?, personalData: String?): String?
}