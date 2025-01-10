package core.qna;

import core.rag.QuestionDTO;

public interface QnaSystem {
    String enrollQuestion(String userId, String title, String category, String contents);
    String updateQuestion(String userId, String questionId, String category, String contents);
    String deleteQuestion(String userId, String questionId);
    QuestionDTO readQuestion(String questionId);
    String readQuestionTitles(String category, int startNum, int endNum);

    String enrollOpinion(String userId, String questionId, String title, String contents);
    String updateOpinion(String userId, String opinionId, String title, String contents);
    String deleteOpinion(String userId, String opinionId);
    String readOpinions(String questionId, int startNum, int endNum);
}
