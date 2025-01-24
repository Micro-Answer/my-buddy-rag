package core.rag.event.handler

import core.rag.event.QnAEvent

interface QnAEventHandler {
    fun handle(event: QnAEvent)
}
