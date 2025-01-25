plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "My-Buddy-RAG"
include("qna")
include("search")
include("explainer")
include("core")
include("cache")
include("qna-with-my-cache")
include("qna-with-spring-cache")
include("qna-with-redis-cache")
include("qna-persistence")
include("rag-with-completable-future")
include("rag-with-coroutine")
include("rag-with-blocking-queue-coroutine")
include("rag-with-blocking-queue-executor-service")
include("util")
include("rag")
include("rag-with-spring-async")
include("rag-with-spring-event-sync")
