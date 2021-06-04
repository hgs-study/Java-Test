package com.javatest;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

//언더스코어 공백 치환
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study(){

        //assertTimeout 은 Duration를 인자로 받고 10초 안에 스터디가 생성되어야 성공
        assertTimeout(Duration.ofSeconds(10), () -> new Study(10));


        // 코드블록({ }) 안에 로직이 300밀리 세컨드로 설정되어있는데 이 300밀리 세컨드를 다 기다리고 테스트가 완료된다.
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        // 코드블록({ }) 안에 로직이 300밀리 세컨드로 설정되어있는데  이 테스트는 100밀리 세컨드가 넘으면 즉시 완료된다
        // 문제점 : ThreadLocal을 사용하는 로직이 있으면 예상치 못한 에러를 발생시킬 수 있다.
        // ThreadLocal : Spring Transaction은 ThreadLocal을 사용하는데, 다른 쓰레드에선 공유가 안 된다.
        // -> 스프링 트랜잭션 설정이 제대로 테스트 안 될 수가 있다. (롤백이 안 되고 디비에 반영될 수 있다.)
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
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