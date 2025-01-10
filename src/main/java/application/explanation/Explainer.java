package application.explanation;

import core.explanation.ExplainerSystem;

class Explainer implements ExplainerSystem {
    @Override
    public String explain(String contents, String personalData) {
        String response = contents + "를 '" + personalData + "' 바탕으로 친절한 설명해주세요";
        System.out.println(response);
        return response;
    }
}
