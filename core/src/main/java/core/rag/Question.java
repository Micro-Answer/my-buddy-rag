package core.rag;

public interface Question {
    String enrollQuestion(String userId, String title, String category, String contents);
    String updateQuestion(String userId, String questionId, String category, String contents);
    String deleteQuestion(String userId, String questionId);
    QuestionDTO readQuestion(String questionId);
    String readQuestionTitles(String category, int startNum, int endNum);
}
