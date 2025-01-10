package application.qna;

import core.rag.OpinionDTO;

public record Opinion(String userId, String questionId, String opinionId, String title, String contents, String createdDate) implements OpinionDTO {
}
