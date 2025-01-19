package com.example.rag.application

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.mockito.Mockito.*
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration

//// Spring Test + Mockito Extension
//@ExtendWith(SpringExtension::class, MockitoExtension::class)
//@SpringBootTest // 전체 Spring 컨텍스트를 로드하는 테스트

@Configuration
@ComponentScan(basePackages = ["com.example.rag.application"])
class TestConfig

@ExtendWith(SpringExtension::class, MockitoExtension::class)
@ContextConfiguration(classes = [TestConfig::class])
class RagApplicationTests {

    @Mock
    lateinit var searchableQnA: SearchableQnA // Mock 객체 생성

    @InjectMocks
    lateinit var rag: Rag // Rag 객체에 Mock된 SearchableQnA를 주입

    @Test
    @DisplayName("test Rag initialization")
    fun initialize() {
    }
}
