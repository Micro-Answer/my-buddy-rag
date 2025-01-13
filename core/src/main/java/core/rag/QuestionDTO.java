package core.rag;

public interface QuestionDTO {
    String userId();
    String questionId();
    String title();
    String category();
    String contents();
    String createdDate();
}
