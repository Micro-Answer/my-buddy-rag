package com.example.rag.qna.domain;

import com.example.rag.qna.adapter.output.opinion.OpinionPersistencePort;
import com.example.rag.qna.adapter.output.question.QuestionPersistencePort;
import core.qna.QnaSystem;
import core.rag.OpinionDTO;
import core.rag.QuestionDTO;
import core.rag.QuestionTitleDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
class Qna implements QnaSystem {
//    private final CacheSystem cache;
    private final QuestionPersistencePort questionPersistencePort;
    private final OpinionPersistencePort opinionPersistencePort;

    @Override
    public String enrollQuestion(String userId, String title, String category, String contents) {
        synchronized (this) {
            questionPersistencePort.saveQuestion(new Question(category, title, contents, userId));
//            cache.putRecentQuestion("questionId", new Question(userId, "questionId", title, category, contents, "createdDate"));
        }
        return "response";
    }

    @Override
    public String updateQuestion(String userId, String questionId, String title, String category, String contents) {
        questionPersistencePort.updateQuestion(new Question(questionId, category, title, contents, userId));
        return "response";
    }

    @Override
    public String deleteQuestion(String userId, String questionId) {
        questionPersistencePort.deleteQuestion(questionId);
        return "response";
    }

    @Override
    public QuestionDTO readQuestion(String questionId) {
        return new Question("userId", "questionId", "title", "category", "contents", LocalDateTime.now(), LocalDateTime.now());

    }

    @Override
    public QuestionTitleDTO[] readQuestionTitles(String category, int startNum, int endNum) {
        return new QuestionTitleDTO[]{
                new QuestionTitle("questionId1", "title", "userId", "createdDate"),
                new QuestionTitle("questionId2", "title", "userId", "createdDate"),
                new QuestionTitle("questionId3", "title", "userId", "createdDate")
        };
    }

    @Override
    public String enrollOpinion(String userId, String questionId, String title, String contents) {
        return "response";
    }

    @Override
    public String updateOpinion(String userId, String opinionId, String title, String contents) {
        return "response";
    }

    @Override
    public String deleteOpinion(String userId, String opinionId) {
        return "response";
    }

    @Override
    public OpinionDTO[] readOpinions(String questionId, int startNum, int endNum) {
        return new OpinionDTO[] {
                new Opinion("questionId", "opinionId1", "title", "contents", "userId", LocalDateTime.now(), LocalDateTime.now())
        };
    }
}
