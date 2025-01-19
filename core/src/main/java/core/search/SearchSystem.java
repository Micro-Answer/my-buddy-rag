package core.search;

public interface SearchSystem {
    String search(String title, String category, String contents);
    String enrollQuestion(String questionId, String title, String category, String contents);
    String updateQuestion(String questionId, String title, String category, String contents);
    String deleteQuestion(String questionId);
}
