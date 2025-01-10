package com.example.rag.application.rag

import core.explanation.ExplainerSystem
import core.qna.QnaSystem
import core.search.SearchSystem

data class RAG(val qna: QnaSystem, val explainer: ExplainerSystem, val search: SearchSystem)
