package com.example.rag.qna.domain;

import core.qna.QnaSystem;
import core.rag.OpinionDTO;
import core.rag.QuestionDTO;
import core.rag.QuestionTitleDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class Qna implements QnaSystem {
//    private final CacheSystem cache;

    @Override
    public String enrollQuestion(String userId, String title, String category, String contents) {
        String response;
        synchronized (this) {
            response = String.format("질문을 등록했습니다 %s %s %s %s", userId, title, category, contents);
//            cache.putRecentQuestion("questionId", new Question(userId, "questionId", title, category, contents, "createdDate"));
        }
        System.out.println(response);
        return response;
    }

    @Override
    public String updateQuestion(String userId, String questionId, String category, String contents) {
        String response = String.format("질문을 수정했습니다 %s %s %s %s", userId, questionId, category, contents);
        System.out.println(response);
        return response;
    }

    @Override
    public String deleteQuestion(String userId, String questionId) {
        String response = String.format("질문을 삭제했습니다 %s %s", userId, questionId);
        System.out.println(response);
        return response;
    }

    @Override
    public QuestionDTO readQuestion(String questionId) {
        String response = String.format("질문을 조회했습니다 %s", questionId);
        System.out.println(response);
        return new Question("userId", "questionId", "title", "category", "contents", "createdDate");

    }

    @Override
    public QuestionTitleDTO[] readQuestionTitles(String category, int startNum, int endNum) {
        String response = String.format("질문 목록을 조회했습니다 %s %d %d", category, startNum, endNum);
        System.out.println(response);
        return new QuestionTitleDTO[]{
                new QuestionTitle("questionId1", "title", "userId", "createdDate"),
                new QuestionTitle("questionId2", "title", "userId", "createdDate"),
                new QuestionTitle("questionId3", "title", "userId", "createdDate")
        };
    }

    @Override
    public String enrollOpinion(String userId, String questionId, String title, String contents) {
        String response = String.format("의견을 등록했습니다 %s %s %s %s", userId, questionId, title, contents);
        System.out.println(response);
        return response;
    }

    @Override
    public String updateOpinion(String userId, String opinionId, String title, String contents) {
        String response = String.format("의견을 수정했습니다 %s %s %s %s", userId, opinionId, title, contents);
        System.out.println(response);
        return response;
    }

    @Override
    public String deleteOpinion(String userId, String opinionId) {
        String response = String.format("의견을 삭제했습니다 %s %s", userId, opinionId);
        System.out.println(response);
        return response;
    }

    @Override
    public OpinionDTO[] readOpinions(String questionId, int startNum, int endNum) {
        String response = String.format("의견들을 조회했습니다 %s %d %d", questionId, startNum, endNum);
        System.out.println(response);
        return new OpinionDTO[] {
                new Opinion("userId", "questionId", "opinionId1", "title", "contents", "createdDate"),
                new Opinion("userId", "questionId", "opinionId2", "title", "contents", "createdDate"),
                new Opinion("userId", "questionId", "opinionId3", "title", "contents", "createdDate")
        };
    }
}
