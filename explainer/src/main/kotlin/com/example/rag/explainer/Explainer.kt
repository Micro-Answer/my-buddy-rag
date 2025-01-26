package com.example.rag.explainer

import core.explanation.ExplainerSystem

class Explainer: ExplainerSystem {
    override fun explain(content: String, personalData: String?) =
        "내용: $content 를 개인정보: $personalData 를 바탕으로 친절한 설명해주세요"
}
