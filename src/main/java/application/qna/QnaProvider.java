package application.qna;

import core.qna.QnaSystem;

public class QnaProvider {
    private static final QnaSystem qna;

    static {
        qna = new Qna();
        System.out.println("Qna 생성");
    }

    public static QnaSystem getQna() {
        return qna;
    }
}
