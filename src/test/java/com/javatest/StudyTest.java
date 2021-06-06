package com.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

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
    @DisplayName("WINDOWS")
    @EnabledOnOs({OS.WINDOWS,OS.LINUX}) // 특정 OS
    @EnabledOnJre(JRE.JAVA_8) //특정 자바 환경
    @EnabledIfEnvironmentVariable(named = "TEST_ENV" ,matches = "LOCAL") //실행 환경
    void EnabledOnOsWindow(){
        System.out.println("window start");
        // assertThat(assertj 사용)
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("mac")
    @DisabledOnOs(OS.MAC) // 맥인 경우에 disable
    void EnabledOnOsMac(){
        System.out.println("mac start");
        // assertThat(assertj 사용)
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);


    }



}