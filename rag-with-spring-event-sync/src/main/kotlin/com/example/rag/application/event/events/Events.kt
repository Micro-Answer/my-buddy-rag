package com.example.rag.application.event.events

import core.rag.event.QnAEvent
import org.springframework.context.ApplicationEvent

class QuestionEnrollEvent(source: Any?, val command: QnAEvent.EnrollQuestion) : ApplicationEvent(source)
class QuestionUpdateEvent(source: Any?, val command: QnAEvent.UpdateQuestion) : ApplicationEvent(source)
class QuestionDeleteEvent(source: Any?, val command: QnAEvent.DeleteQuestion) : ApplicationEvent(source)
class OpinionEnrollEvent(source: Any?, val command: QnAEvent.EnrollOpinion) : ApplicationEvent(source)
class OpinionUpdateEvent(source: Any?, val command: QnAEvent.UpdateOpinion) : ApplicationEvent(source)
class OpinionDeleteEvent(source: Any?, val command: QnAEvent.DeleteOpinion) : ApplicationEvent(source)
