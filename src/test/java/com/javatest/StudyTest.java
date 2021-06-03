package com.javatest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    void createTest(){
        Study study = new Study();
        assertNotNull(study);
        System.out.println(" create Test! ");
    }

    @Test
    @Disabled
    void createTest2(){
        System.out.println(" create Test2! ");
    }

    //테스트 클래스 안에 있는 여러 테스트가 모두 실행하기 전에 딱 한 번 호출이 된다.
    //반드시 static 메서드를 사용해야한다. private(x) defalut(o) 리턴타입(x)
    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll ");
    }

    //모든 테스트 실행 이후 한번만 호출된다.
    //반드시 static 메서드를 사용해야한다.
    @AfterAll
    static void afterAll(){
        System.out.println("afterAll");
    }

    // 모든 테스트를 실행하기 이전에 한 번 실행된다.
    // 스태틱일 필요는 없다.
    @BeforeEach
    void beforeEach(){
        System.out.println("beforeEach");
    }

    // 모든 테스트를 실행하기 이후에 한 번 실행된다.
    // 스태틱일 필요는 없다.
    @AfterEach
    void afterEach(){
        System.out.println("afterEach");
    }

}