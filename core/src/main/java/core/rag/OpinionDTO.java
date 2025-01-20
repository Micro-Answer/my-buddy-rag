package core.rag;

import java.time.LocalDateTime;

public interface OpinionDTO {
    String userId();
    String questionId();
    String opinionId();
    String title();
    String contents();
    LocalDateTime createdAt();
    LocalDateTime updatedAt();
}
