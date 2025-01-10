package core.rag;

public interface Opinion {
    String enrollOpinion(String userId, String questionId, String title, String contents);
    String updateOpinion(String userId, String opinionId, String title, String contents);
    String deleteOpinion(String userId, String opinionId);
    String readOpinions(String questionId, int startNum, int endNum);
}
