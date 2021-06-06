package com.javatest;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

//언더스코어 공백 치환
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study(){

        // assertThat(assertj 사용)
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);


    }

    @Test
    @DisplayName("assumeTrue")
    void assumeTrueTest(){
        //assumeTrue : 뒤에 테스트가 실행되지 않는다.
        String test_env = "LOCAL";
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        // assertThat(assertj 사용)
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("assumeTrue")
    void assumingThatTest(){
        String test_env = "LOCAL";

        //assumingThat : 앞에 조건이 만족하면 실행 로직 사용
        assumingThat("HGS".equalsIgnoreCase(test_env),()->{
            // 실행 로직
        });

        assumingThat("HGS".equalsIgnoreCase(test_env),()->{
            // 실행 로직
        });
    }

    @Test
    void create_new_study_again(){
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