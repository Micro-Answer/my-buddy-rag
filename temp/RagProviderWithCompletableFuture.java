package application.rag;

import application.explanation.ExplainerProvider;
import application.qna.QnaProvider;
import application.search.SearchToolProvider;
import core.rag.RagSystem;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RagProviderWithCompletableFuture {
    private static RagSystem rag;

    static {
        CompletableFuture<Void> future = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    QnaProvider.getQna(); // 비동기 처리
                }),
                CompletableFuture.runAsync(() -> {
                    ExplainerProvider.getExplainer(); // 비동기 처리
                }),
                CompletableFuture.runAsync(() -> {
                    SearchToolProvider.getSearchTool(); // 비동기 처리
                })
        );

        future.thenRun(() -> {
            rag = new RAG(
                    QnaProvider.getQna(),
                    ExplainerProvider.getExplainer(),
                    SearchToolProvider.getSearchTool()
            );
            System.out.println("RAG 생성");
        });

        try {
            future.get(); // 모든 작업이 완료될 때까지 block
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static RagSystem getRag() {
        return rag;
    }
}
