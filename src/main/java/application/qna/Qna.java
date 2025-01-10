package application.qna;

import core.qna.QnaSystem;
import core.rag.OpinionDTO;
import core.rag.QuestionDTO;
import core.rag.QuestionTitleDTO;

class Qna implements QnaSystem {

    @Override
    public String enrollQuestion(String userId, String title, String category, String contents) {
        String response = "질문을 등록했습니다 " + String.format("%s %s %s %s", userId, title, category, contents);
        System.out.println(response);
        return response;
    }

    @Override
    public String updateQuestion(String userId, String questionId, String category, String contents) {
        String response = "질문을 수정했습니다 " + String.format("%s %s %s %s", userId, questionId, category, contents);
        System.out.println(response);
        return response;
    }

    @Override
    public String deleteQuestion(String userId, String questionId) {
        String response = "질문을 삭제했습니다 " + String.format("%s %s", userId, questionId);
        System.out.println(response);
        return response;
    }

    @Override
    public QuestionDTO readQuestion(String questionId) {
        String response = "질문을 조회했습니다 " + String.format("%s", questionId);
        System.out.println(response);
        return new Question("userId", "questionId", "title", "category", "contents", "createdDate");

    }

    @Override
    public QuestionTitleDTO[] readQuestionTitles(String category, int startNum, int endNum) {
        String response = "질문 목록을 조회했습니다 " + String.format("%s %d %d", category, startNum, endNum);
        System.out.println(response);
        return new QuestionTitleDTO[]{
                new QuestionTitle("questionId1", "title", "userId", "createdDate"),
                new QuestionTitle("questionId2", "title", "userId", "createdDate"),
                new QuestionTitle("questionId3", "title", "userId", "createdDate")
        };
    }

    @Override
    public String enrollOpinion(String userId, String questionId, String title, String contents) {
        String response = "의견을 등록했습니다 " + String.format("%s %s %s %s", userId, questionId, title, contents);
        System.out.println(response);
        return response;
    }

    @Override
    public String updateOpinion(String userId, String opinionId, String title, String contents) {
        String response = "의견을 수정했습니다 " + String.format("%s %s %s %s", userId, opinionId, title, contents);
        System.out.println(response);
        return response;
    }

    @Override
    public String deleteOpinion(String userId, String opinionId) {
        String response = "의견을 삭제했습니다 " + String.format("%s %s", userId, opinionId);
        System.out.println(response);
        return response;
    }

    @Override
    public OpinionDTO[] readOpinions(String questionId, int startNum, int endNum) {
        String response = "의견들을 조회했습니다 " + String.format("%s %d %d", questionId, startNum, endNum);
        System.out.println(response);
        return new OpinionDTO[] {
                new Opinion("userId", "questionId", "opinionId1", "title", "contents", "createdDate"),
                new Opinion("userId", "questionId", "opinionId2", "title", "contents", "createdDate"),
                new Opinion("userId", "questionId", "opinionId3", "title", "contents", "createdDate")

        };
    }
}
