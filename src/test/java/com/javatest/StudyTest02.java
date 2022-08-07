//package com.javatest;
//
//import com.javatest.domain.Study;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ParameterContext;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.aggregator.AggregateWith;
//import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
//import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
//import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
//import org.junit.jupiter.params.converter.ArgumentConversionException;
//import org.junit.jupiter.params.converter.SimpleArgumentConverter;
//import org.junit.jupiter.params.provider.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assumptions.assumeTrue;
//import static org.junit.jupiter.api.Assumptions.assumingThat;
//
////언더스코어 공백 치환
////@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // 외부 설정으로 변경
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //테스트 실행 순서 정해줄 수 있다.
//class StudyTest02 {
//
//    int value = 0;
//
////    [START] Tag
//    @Order(2)
//    @DisplayName("fastTest 어노테이션 적용")
//    @Disabled
//    @FastTest   // 커스텀 어노테이션을 사용할 경우 <-> 기존의 @TAG("fast")는 타입 세이프하지 않다. fasd, fadt 등 오타가 날 수 있다.
//    void create_new_study_fast(){
//        System.out.println("fast start : "+value);
//        value++;
//        Study actual = new Study(10);
//        assertThat(actual.getLimit()).isGreaterThan(0);
//    }
//
//    @Order(1)
//    @DisplayName("slowTest 어노테이션 적용")
//    @SlowTest
//    void create_new_study_slow(){
//        System.out.println("slow start : " + value);
//        value++;
//        Study actual = new Study(10);
//        assertThat(actual.getLimit()).isGreaterThan(0);
//    }
//// [END] Tag
//
//
//    //반복하고 싶은 테스트
//    @DisplayName("반복 테스트")
//    @RepeatedTest(value = 10, name="{displayName}, {currentRepetition} / {totalRepetitions}")
//    void RepeatedTest_10(RepetitionInfo repetitionInfo){
//        //repetitionInfo를 인자로 받아서 총 몇번 중 몇번 째 테스트인지 확인 가능
//        System.out.println("Test" + repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
//    }
//
//    //파라미터 테스트 (파라미터 갯수만큼 테스트)
//    @DisplayName("파라미터 테스트")
//    @ParameterizedTest(name="{index} {displayName} message={0} ")
//    @ValueSource(strings = {"날씨가","많이","추워지고","있네요."})
//    @EmptySource
//    @NullSource
//    void parameterizedTest(String message){
//        System.out.println("message = " + message);
//    }
//
//    //파라미터 테스트 (파라미터 갯수만큼 테스트)
//    @DisplayName("파라미터 테스트")
//    @ParameterizedTest(name="{index} {displayName} message={0} ")
//    @CsvSource({"10,'자바 스터디'","20, '스프링 스터디'"})
//    void csvSource(Integer limit, String name){
//        Study study = new Study(limit,name);
//        System.out.println(study);
//    }
//
//    //ArgumentsAccessor 활용해서 인자 여러개 받을 수 있음
//    @DisplayName("파라미터 테스트")
//    @ParameterizedTest(name="{index} {displayName} message={0} ")
//    @CsvSource({"10,'자바 스터디'","20, '스프링 스터디'"})
//    void csvSources(ArgumentsAccessor argumentsAccessor){
//        Study study = new Study(argumentsAccessor.getInteger(0), //첫번쨰 위치(10,20)에서 꺼냄
//                                argumentsAccessor.getString(1) //두번째 위치(자바스터디, 스프링 스터디)에서 꺼냄
//                                );
//        System.out.println(study);
//    }
//
//
//
//    //Aggregator 활용해서 인자 여러개 받을 수 있음
//    @DisplayName("파라미터 테스트")
//    @ParameterizedTest(name="{index} {displayName} message={0} ")
//    @CsvSource({"10,'자바 스터디'","20, '스프링 스터디'"})
//    void csvSources(@AggregateWith(StudyAggregator.class) Study study){
//        System.out.println(study);
//    }
//
//    //퍼블릭 클래스 이거나 이너 스태틱 클래스여야한다.
//    static class StudyAggregator implements ArgumentsAggregator{
//        @Override
//        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
//            return new Study(accessor.getInteger(0), accessor.getString(1));
//        }
//    }
//
//
//
//    static class StudyConverter extends SimpleArgumentConverter{
//        @Override
//        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
//            assertEquals(Study.class, targetType, "Can only convert to Study");
//            return new Study(Integer.parseInt(source.toString()));
//        }
//    }
//
//
//}