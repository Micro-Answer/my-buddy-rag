import application.rag.RagProvider;
import core.rag.RagSystem;

public class Main {
    public static void main(String[] args) {
        RagSystem rag = RagProvider.getRag();
        System.out.println("An Application Starts!!!");
    }
}
