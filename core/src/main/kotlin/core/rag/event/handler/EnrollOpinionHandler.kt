package core.rag.event.handler

import core.qna.QnaSystem
import core.rag.event.QnAEvent

class EnrollOpinionHandler(private val qna: QnaSystem) : QnAEventHandler {
    override fun handle(event: QnAEvent) {
        if (event is QnAEvent.EnrollOpinion) {
            qna.enrollOpinion(event)
        }
    }
}