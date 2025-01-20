package com.example.rag.explainer;

import core.explanation.ExplainerSystem;
import org.springframework.stereotype.Component;

@Component
class Explainer implements ExplainerSystem {
    @Override
    public String explain(String contents, String personalData) {
        String response = contents + "를 '" + personalData + "' 바탕으로 친절한 설명해주세요";
        System.out.println(response);
        return response;
    }
}
