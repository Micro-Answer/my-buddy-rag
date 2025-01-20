package core.rag;

import java.time.LocalDateTime;

public interface QuestionDTO {
    String userId();
    String questionId();
    String title();
    String category();
    String contents();
    LocalDateTime createdAt();
    LocalDateTime updatedAt();
}
