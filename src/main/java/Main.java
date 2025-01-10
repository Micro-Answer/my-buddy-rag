import com.example.rag.application.rag.RagProvider;
import core.rag.RagSystem;

public class Main {
    public static void main(String[] args) {
        RagSystem rag = RagProvider.INSTANCE.getRag();
        System.out.println("An Application Starts!!!");

        // 질문 등록
        rag.enrollQuestion(
                "사용자 아이디: goodDay",
                "제목: 갑자기 치아가 부러졌어요 어떻게 해야하죠?",
                "카테고리: 식품",
                "아이스크림을 막다가 치아가 부러졌어요 어떡하면 좋죠?");

        // 질문 수정 - 카테고리 수정
        rag.updateQuestion(
                "사용자 아이디: goodDay",
                "질문 식별자: 1",
                "카테고리: 의료",
                null);

        // 질문 삭제
        rag.deleteQuestion("사용자 아이디: goodDay", "질문 식별자: 1");

        // 질문 조회
        rag.readQuestion("질문 식별자: 1");

        // 질문 타이틀 목록 조회
        rag.readQuestionTitles(null, 1, 10); // 전체 카테고리(null), 시작 번호, 끝 번호
        rag.readQuestionTitles("카테고리: 치아", 1, 10);

        // 의견 등록
        rag.enrollOpinion(
                "사용자 아이디: betterDay",
                "질문 식별자: 1",
                "제목: 부러진 치아를 ~~처리하고 빨리 치과에 가세요",
                "주변에서 ~~를 할 수 있다면 긴급 처리하시고 가능한 빨리 병원에 가서 치료받으세요");

        // 의견 수정
        rag.updateOpinion(
                "사용자 아이디: betterDay",
                "의견 식별자: 1",
                null,
                "주변에서 ~~를 할 수 있다면 긴급 처리하시고 가능한 빨리 병원에 가서 치료받으세요. 그리고 ~~하시면 좋아요");

        // 의견 삭제
        rag.deleteOpinion(
                "사용자 아이디: betterDay",
                "의견 식별자: 1");

        // 의견 목록 조회
        rag.readOpinions("질문 식별자: 1", 1, 10);

        // 맞춤형 해설 질의응답 검색
        rag.search("치아가 부러졌습니다 어떻게 하면 좋을까요?", 10, null, "개인정보: 일반 초등학생");
    }
}
