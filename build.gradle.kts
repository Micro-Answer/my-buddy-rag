plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":core"))
	implementation(project(":qna"))
	implementation(project(":search"))
	implementation(project(":explainer"))

	implementation(project(":project-basic"))
//	implementation(project(":project-basic-with-cache"))
//	implementation(project(":project-basic-with-cache-queue"))
//	implementation(project(":project-basic-with-cache-queue-executor-service"))
//	implementation(project(":project-basic-with-cache-queue-completable-future"))
//	implementation(project(":project-basic-with-cache-queue-spring-async"))
//	implementation(project(":project-event-bus"))
//	implementation(project(":project-event-bus-with-cache"))
//	implementation(project(":project-event-bus-with-cache-queue"))
//	implementation(project(":project-event-bus-with-cache-queue-rabbitmq"))
//	implementation(project(":project-event-bus-with-cache-queue-spring-event"))
//	implementation(project(":project-multi-instance-with-redis-rabbitmq"))

//	implementation("org.springframework.boot:spring-boot-starter-actuator")
//	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-data-redis")
//	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.jsoup:jsoup:1.18.3")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//	testImplementation("org.springframework.amqp:spring-rabbit-test")
//	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
