package com.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

//언더스코어 공백 치환
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest02 {

//    [START] Tag
    @DisplayName("fastTest 어노테이션 적용")
    @FastTest   // 커스텀 어노테이션을 사용할 경우 <-> 기존의 @TAG("fast")는 타입 세이프하지 않다. fasd, fadt 등 오타가 날 수 있다.
    void create_new_study_fast(){
        System.out.println("fast start");

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @DisplayName("slowTest 어노테이션 적용")
    @SlowTest
    void create_new_study_slow(){
        System.out.println("slow start");

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }
//    [END] Tag
}