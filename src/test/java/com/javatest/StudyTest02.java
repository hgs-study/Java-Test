package com.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
// [END] Tag


    //반복하고 싶은 테스트
    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10, name="{displayName}, {currentRepetition} / {totalRepetitions}")
    void RepeatedTest_10(RepetitionInfo repetitionInfo){
        //repetitionInfo를 인자로 받아서 총 몇번 중 몇번 째 테스트인지 확인 가능
        System.out.println("Test" + repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
    }

    //파라미터 테스트 (파라미터 갯수만큼 테스트)
    @DisplayName("파라미터 테스트")
    @ParameterizedTest(name="{index} {displayName} message={0} ")
    @ValueSource(strings = {"날씨가","많이","추워지고","있네요."})
    @EmptySource
    @NullSource
    void parameterizedTest(String message){
        System.out.println("message = " + message);
    }

    //파라미터 테스트 (파라미터 갯수만큼 테스트)
    @DisplayName("파라미터 테스트")
    @ParameterizedTest(name="{index} {displayName} message={0} ")
    @ValueSource(ints = {10,20,30})
//    @CsvSource({"10,'자바 스터디'","20, '스프링 스터디'"})
    void csvSource(@ConvertWith(StudyConverter.class) Study study){
        System.out.println("study.getLimit() = " + study.getLimit());
    }
    
    static class StudyConverter extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }


}