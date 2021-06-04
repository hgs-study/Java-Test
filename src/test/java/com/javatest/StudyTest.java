package com.javatest;

import org.junit.jupiter.api.*;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

//언더스코어 공백 치환
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study(){
        Study study = new Study(-10);
        assertNotNull(study);

        //(기댓값, 실제값, 메세지) :기댓값 <-> 실제값 바뀌어도 제대로 동작한다.
        assertEquals(StudyStatus.DRAFT,study.getStatus(),"스터디를 처음 만들면 상태값이 DRAFT여야한다.");


        assertEquals(StudyStatus.DRAFT,study.getStatus(),
                "스터디를 처음 만들면 상태값이 "+StudyStatus.DRAFT+"여야한다.");

        //람다 서플라이어로 작성 가능 (실패하면 "+StudyStatus.DRAFT+" 부분 문자열 연산을 안 함
        //하지만 람다를 사용하지 않을 경우 성공을 하든 실패를하든 문자열 연산을 한다.
        assertEquals(StudyStatus.DRAFT,study.getStatus(),
                () -> "스터디를 처음 만들면 상태값이 "+StudyStatus.DRAFT+"여야한다.");


        assertTrue( study.getLimit() > 0, "스터디 참석 가능 인원은 0보다 커야한다.");


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