plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "My-Buddy-RAG"
include("qna")
include("search")
include("explainer")
include("project-basic")
include("project-basic-with-cache-queue")
include("project-basic-with-cache-queue-executor-service")
include("project-basic-with-cache-queue-completable-future")
include("project-basic-with-cache-queue-spring-async")
include("project-event-bus")
include("project-event-bus-with-cache")
include("project-event-bus-with-cache-queue")
include("project-event-bus-with-cache-queue-rabbitmq")
include("project-event-bus-with-cache-queue-spring-event")
include("project-multi-instance-with-redis-rabbitmq")
include("core")
include("cache")
include("qna-with-my-cache")
include("qna-with-spring-cache")
include("qna-with-redis-cache")
include("qna-persistence")
