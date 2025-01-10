package application.explanation;

import core.explanation.ExplainerSystem;

public class ExplainerProvider {
    private static final ExplainerSystem explainer;

    static {
        explainer = new Explainer();
        System.out.println("Explainer 생성");
    }

    public static ExplainerSystem getExplainer() {
        return explainer;
    }
}
