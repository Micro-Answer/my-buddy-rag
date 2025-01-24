package core.rag.event.handler

import core.qna.QnaSystem
import core.rag.event.QnAEvent
import core.search.SearchSystem

class UpdateQuestionHandler(private val qna: QnaSystem, private val search: SearchSystem) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.UpdateQuestion) {
            qna.updateQuestion(event)
            search.updateQuestion(event)
        }
    }
}