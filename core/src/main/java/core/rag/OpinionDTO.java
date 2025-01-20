package core.rag;

import java.time.LocalDateTime;

public interface OpinionDTO {
    String getOpinionId();
    String getQuestionId();
    String getTitle();
    String getContent();
    String getUserId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
