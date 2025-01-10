package application.qna;

import core.rag.QuestionDTO;

public record Question(String userId, String questionId, String title, String category, String contents, String createdDate) implements QuestionDTO {
}
