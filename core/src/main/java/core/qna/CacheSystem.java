package core.qna;

import core.rag.QuestionDTO;
import core.rag.QuestionTitleDTO;

public interface CacheSystem {
    QuestionTitleDTO[] getRecentQuestionTitles(String category, int startNum, int endNum);
    void putRecentQuestionTitle(String category, QuestionTitleDTO questionTitle);
    QuestionDTO getRecentQuestion(String questionId);
    void putRecentQuestion(String questionId, QuestionDTO question);
}
